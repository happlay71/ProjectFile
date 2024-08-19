package com.hmall.trade.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.api.client.CartClient;
import com.hmall.api.client.ItemClient;
import com.hmall.api.client.PayClient;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.ItemDoc;
import com.hmall.api.dto.OrderDetailDTO;
import com.hmall.api.dto.OrderFormDTO;
import com.hmall.common.exception.BadRequestException;
import com.hmall.common.utils.RabbitMqHelper;
import com.hmall.common.utils.UserContext;
import com.hmall.trade.domain.po.Order;
import com.hmall.trade.domain.po.OrderDetail;
import com.hmall.trade.mapper.OrderMapper;
import com.hmall.trade.service.IOrderDetailService;
import com.hmall.trade.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hmall.common.constants.MQConstants.*;
import static com.hmall.common.constants.MQConstants.ES_UPDATE_SOLD_KEY;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final ItemClient itemClient;

    private final IOrderDetailService detailService;

    private final CartClient cartClient;

    private final PayClient payClient;

    private final RabbitTemplate rabbitTemplate;

    private final RabbitMqHelper rabbitMqHelper;

    @Override
    @Transactional
//    @GlobalTransactional
    public Long createOrder(OrderFormDTO orderFormDTO) {
        // 1.订单数据
        Order order = new Order();
        // 1.1.查询商品
        List<OrderDetailDTO> detailDTOS = orderFormDTO.getDetails();
        // 1.2.获取商品id和数量的Map
        Map<Long, Integer> itemNumMap = detailDTOS.stream()
                .collect(Collectors.toMap(OrderDetailDTO::getItemId, OrderDetailDTO::getNum));
        Set<Long> itemIds = itemNumMap.keySet();
        // 1.3.查询商品
//        List<ItemDTO> items = itemService.queryItemByIds(itemIds);
        List<ItemDTO> items = itemClient.queryItemByIds(itemIds);
        if (items == null || items.size() < itemIds.size()) {
            throw new BadRequestException("商品不存在");
        }
        // 1.4.基于商品价格、购买数量计算商品总价：totalFee
        int total = 0;
        for (ItemDTO item : items) {
            total += item.getPrice() * itemNumMap.get(item.getId());
        }
        order.setTotalFee(total);
        // 1.5.其它属性
        order.setPaymentType(orderFormDTO.getPaymentType());
        order.setUserId(UserContext.getUser());
        order.setStatus(1);
        // 1.6.将Order写入数据库order表中
        save(order);

        // 2.保存订单详情
        List<OrderDetail> details = buildDetails(order.getId(), items, itemNumMap);
        detailService.saveBatch(details);

        // 3.清理购物车商品
        try {
            rabbitTemplate.convertAndSend("trade.topic", "order.create", itemIds, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    Long userId = UserContext.getUser();
                    if (userId == null) {
                        throw new AmqpException("User ID is null");
                    }
                    message.getMessageProperties().setHeader("userId", userId);
                    return message;
                }
            });
        } catch (AmqpException e) {
            // 记录详细日志
            log.error("Failed to send message to RabbitMQ: {}", e);
            throw new RuntimeException("清理购物车失败！");
        }
//        cartClient.deleteCartItemByIds(itemIds);

        // 4.扣减库存, 增加销量
        // 修改es销量数据
        List<ItemDoc> itemDocs = items.stream()
                .map(item -> {
                    ItemDoc itemDoc = BeanUtil.copyProperties(item, ItemDoc.class);
                    itemDoc.setSold(itemDoc.getSold() + itemNumMap.get(item.getId()));
                    return itemDoc;
                })
                .collect(Collectors.toList());

        if (itemDocs.isEmpty()) {
            throw new RuntimeException("待增加销量的商品id为空");
        }

        try {
            rabbitMqHelper.sendMessage(ES_DOC_EXCHANGE_NAME, ES_UPDATE_SOLD_KEY, itemDocs);
        } catch (Exception e) {
            throw new RuntimeException("ES增销量消息发送失败", e);
        }

        for (ItemDTO item : items) {
            item.setSold(item.getSold() + itemNumMap.get(item.getId()));
            itemClient.updateItem(item);
        }

        try {
            itemClient.deductStock(detailDTOS);
        } catch (Exception e) {
            throw new RuntimeException("库存不足！");
        }




        System.out.println("订单下单成功：" + order.getId());

        // 5.发送延迟消息，检测订单状态
        rabbitMqHelper.sendDelayMessage(
                DELAY_EXCHANGE_NAME,
                DELAY_ORDER_KEY,
                order.getId(),
                10000);

        return order.getId();
    }

    @Override
    public void markOrderPaySuccess(Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(2);
        order.setPayTime(LocalDateTime.now());
        updateById(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        // 标记订单为已关闭
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(5);
        order.setPayTime(LocalDateTime.now());
        order.setCloseTime(LocalDateTime.now());
        updateById(order);

        // 修改支付信息为超时未支付或取消
        try {
            payClient.cancelPayOrderByBizOrderNo(orderId);
        } catch (Exception e) {
            throw new RuntimeException("未支付订单，状态修改失败", e);
        }

        // 恢复库存
        // 1.获取订单中物品id和购买数量
        List<OrderDetail> orderDetails = detailService.query().eq("order_Id", orderId).list();
//        List<Map<Long, Integer>> items = orderDetails.stream().map(orderDetail -> {
//            Map<Long, Integer> item = new HashMap<>();
//            item.put(orderDetail.getItemId(), orderDetail.getNum());
//            return item;
//        }).collect(Collectors.toList());
        //高科技版
        Map<Long, Integer> items = orderDetails.stream()
                .collect(Collectors.toMap(OrderDetail::getItemId, OrderDetail::getNum));

        // 2.恢复订单中物品的全部库存
        Set<Long> itemIds = items.keySet();
        List<ItemDTO> itemDTOS = itemClient.queryItemByIds(itemIds);

        // 2.1修改es销量数据
        List<ItemDoc> itemDocs = itemDTOS.stream()
                .map(item -> {
                    ItemDoc itemDoc = BeanUtil.copyProperties(item, ItemDoc.class);
                    itemDoc.setSold(itemDoc.getSold() - items.get(item.getId()));
                    return itemDoc;
                })
                .collect(Collectors.toList());

        if (itemDocs.isEmpty()) {
            throw new RuntimeException("待减少销量的商品id为空");
        }

        try {
            rabbitMqHelper.sendMessage(ES_DOC_EXCHANGE_NAME, ES_UPDATE_SOLD_KEY, itemDocs);
        } catch (Exception e) {
            throw new RuntimeException("ES减销量消息发送失败", e);
        }

        // 2.2修改数据库信息
        for (ItemDTO itemDTO : itemDTOS) {
            itemDTO.setStock(itemDTO.getStock() + items.get(itemDTO.getId()));
            itemDTO.setSold(itemDTO.getSold() - items.get(itemDTO.getId()));
            itemClient.updateItem(itemDTO);
        }
    }

    private List<OrderDetail> buildDetails(Long orderId, List<ItemDTO> items, Map<Long, Integer> numMap) {
        List<OrderDetail> details = new ArrayList<>(items.size());
        for (ItemDTO item : items) {
            OrderDetail detail = new OrderDetail();
            detail.setName(item.getName());
            detail.setSpec(item.getSpec());
            detail.setPrice(item.getPrice());
            detail.setNum(numMap.get(item.getId()));
            detail.setItemId(item.getId());
            detail.setImage(item.getImage());
            detail.setOrderId(orderId);
            details.add(detail);
        }
        return details;
    }
}
