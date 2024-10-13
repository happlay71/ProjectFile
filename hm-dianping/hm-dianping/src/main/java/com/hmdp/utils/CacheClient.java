package com.hmdp.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmdp.entity.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.hmdp.utils.RedisConstants.*;

@Slf4j
@Component
public class CacheClient {
    private final StringRedisTemplate stringRedisTemplate;

    public CacheClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void set(String key, Object value, Long time, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit) {
        RedisData redisData = new RedisData();
        // 将设定的过期时间转换为秒插入
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        redisData.setData(value);
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    public <R, ID> R queryWithPassThrough(
            String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        // 1.从redis中查询商铺缓存
        String key = keyPrefix + id;
        String Json = stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否存在
        if (StrUtil.isNotBlank(Json)) {
            // 3.存在则返回
            return JSONUtil.toBean(Json, type);
        }
        // 判断命中是否为空值
        if (Json != null) {
            return null;
        }

        // 4.不存在，根据id查询数据库
        // 函数式编程
        R r = dbFallback.apply(id);
        // 5.不存在，报错
        if (r == null) {
            // 将空值写入redis
            stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        // 6.存在，写入redis
        String jsonStr = JSONUtil.toJsonStr(r);
        this.set(key, r, time, unit);

        return r;
    }

    // 创建线程池
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    public <R, ID> R queryWithLogicalExpire(
            String keyPrefix, String lockKeyPrefix, ID id,
            Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        // 1.从redis中查询商铺缓存
        String key = keyPrefix + id;
        String Json = stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否命中
        if (StrUtil.isBlank(Json)) {
            // 3.未命中则返回
            return null;
        }

        // 4.命中，将json反序列化为对象
        RedisData redisData = JSONUtil.toBean(Json, RedisData.class);
        LocalDateTime expireTime = redisData.getExpireTime();
        JSONObject data = (JSONObject) redisData.getData();
        R r = JSONUtil.toBean(data, type);

        // 5.判断是否过期
        if (expireTime.isAfter(LocalDateTime.now())) {
            // 5.1未过期，直接返回店铺信息
            return r;
        }

        // 6.过期，重建缓存
        // 6.1获取互斥锁
        String loginKey = lockKeyPrefix + id;
        boolean lock = tryLock(loginKey);
        // 6.2判断是否获取
        if (lock) {
            // 2.再次判断是否命中
            if (StrUtil.isNotBlank(Json) && expireTime.isAfter(LocalDateTime.now())) {
                // 3.命中则返回
                unLock(loginKey);
                return r;
            }

            // 6.3成功，开启独立线程，实现缓存重建
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
//                    this.saveShopToRedis(id, 20L);
                    // 查询数据库
                    R r1 = dbFallback.apply(id);
                    // 写入redis
                    this.setWithLogicalExpire(key, r1, time, unit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放锁
                    unLock(loginKey);
                }
            });

        }

        // 6.4失败，返回旧店铺信息
        return r;
    }

    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    private void unLock(String key) {
        stringRedisTemplate.delete(key);
    }

    public <R, ID> void saveToRedis(ID id, Function<ID, R> dbFallback, Long expireSeconds) throws InterruptedException {
        // 1.查询店铺数据
        R r = dbFallback.apply(id);
//        Thread.sleep(200);
        // 2.封装逻辑过期时间
        RedisData redisData = new RedisData();
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(expireSeconds));
        redisData.setData(r);
        // 3.写入Redis
        stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(redisData));
    }
}
