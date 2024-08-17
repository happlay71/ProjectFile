package com.hmall.item.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.RabbitMqHelper;
import com.hmall.item.domain.po.Item;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.mapper.ItemMapper;
import com.hmall.item.service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.hmall.common.constants.MQConstants.*;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 虎哥
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    private final RabbitMqHelper rabbitMqHelper;

    @Override
    @Transactional
    public void deductStock(List<OrderDetailDTO> items) {
        // 获取item信息，转成itemDco
        List<ItemDoc> itemDocs = items.stream().map(item -> {
            Item oldItem = this.getById(item.getItemId());
            if (oldItem == null) {
                throw new RuntimeException("商品id不存在: " + item.getItemId());
            }
            oldItem.setSold(oldItem.getSold() + item.getNum());
            return BeanUtil.copyProperties(oldItem, ItemDoc.class);
        }).collect(Collectors.toList());
        try {
            rabbitMqHelper.sendMessage(ES_DOC_EXCHANGE_NAME, ES_UPDATE_SOLD_KEY, itemDocs);
        } catch (Exception e) {
            throw new RuntimeException("ES增销量消息发送失败", e);
        }

        // 数据库操作
        String sqlStatement = "com.hmall.item.mapper.ItemMapper.updateStock";
        boolean result = false;
        try {
            result = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            throw new BizIllegalException("更新库存异常，可能是库存不足!", e);
        }
        if (!result) {
            throw new BizIllegalException("库存不足！");
        }
    }

    @Override
    public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ItemDTO.class);
    }

    @Override
    public void saveItem(ItemDTO item) {
        // 转换为文档实体类
        ItemDoc itemDoc = BeanUtil.copyProperties(item, ItemDoc.class);
        // 向ES发消息
        try {
            rabbitMqHelper.sendMessage(ES_DOC_EXCHANGE_NAME, ES_INDEX_KEY, itemDoc);
        } catch (Exception e) {
            throw new RuntimeException("ES新增文档消息发送失败", e);
        }
        // 新增
        this.save(BeanUtils.copyBean(item, Item.class));
    }

    @Override
    public void updateItemById(ItemDTO item) {
        // 转换为文档实体类
        ItemDoc itemDoc = BeanUtil.copyProperties(item, ItemDoc.class);
        // 向ES发消息
        try {
            rabbitMqHelper.sendMessage(ES_DOC_EXCHANGE_NAME, ES_INDEX_KEY, itemDoc);
        } catch (Exception e) {
            throw new RuntimeException("ES更新文档消息发送失败", e);
        }
        this.updateById(BeanUtils.copyBean(item, Item.class));
    }

    @Override
    public void updateStatusById(Long id, Integer status) {
        if (status != 1) {
            // 向ES发删除消息
            try {
                rabbitMqHelper.sendMessage(ES_DOC_EXCHANGE_NAME, ES_DELETE_KEY, id);
            } catch (Exception e) {
                throw new RuntimeException("ES商品状态更新文档消息发送失败", e);
            }
        }

        Item item = new Item();
        item.setId(id);
        item.setStatus(status);
        this.updateById(item);
    }

    @Override
    public void removeItemById(Long id) {
        // 向ES发删除消息
        try {
            rabbitMqHelper.sendMessage(ES_DOC_EXCHANGE_NAME, ES_DELETE_KEY, id);
        } catch (Exception e) {
            throw new RuntimeException("ES根据id删除商品文档消息发送失败", e);
        }

        this.removeById(id);
    }
}
