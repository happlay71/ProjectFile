package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.SettingUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryAllTypeList() {

        // 1.从redis缓存获取数据
        Long max = stringRedisTemplate.opsForSet().size("shop:type");
        System.out.println(max);
        // 2.成功，则返回
        if (max != null && max != 0) {
            Set<String> shopTypeSets = stringRedisTemplate.opsForSet().members("shop:type");
            System.out.println(shopTypeSets);
            List<ShopType> shopTypes = new ArrayList<>();
            if (shopTypeSets != null) {
                for (String shopTypeSet : shopTypeSets) {
                    shopTypes.add(JSONUtil.toBean(shopTypeSet, ShopType.class, false));
                }
            }
            return Result.ok(shopTypes);
        }

        // 3.从数据库中获取数据
        List<ShopType> typeList = query().orderByAsc("sort").list();
        // 4.不存在，则报错
        if (typeList.isEmpty()) {
            return Result.fail("没有店铺类型");
        }
        // 5.存在，则将其转换成List集合存入redis
        Set<String> shopTypeSets = typeList.stream().map(JSONUtil::toJsonStr).collect(Collectors.toSet());
        System.out.println("shopTypeSets" + shopTypeSets);
        for (String shopTypeSet : shopTypeSets) {
            System.out.println("shopTypeSet" + shopTypeSet);
            stringRedisTemplate.opsForSet().add("shop:type", shopTypeSet);
        }
        // 6.返回
        return Result.ok(typeList);
    }
}
