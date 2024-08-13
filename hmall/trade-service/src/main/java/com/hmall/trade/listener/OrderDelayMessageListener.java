package com.hmall.trade.listener;

import com.hmall.api.client.PayClient;
import com.hmall.api.dto.PayOrderDTO;
import com.hmall.trade.constants.MQConstants;
import com.hmall.trade.domain.po.Order;
import com.hmall.trade.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.hmall.trade.constants.MQConstants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderDelayMessageListener {
    private final IOrderService orderService;

    private final PayClient payClient;
    
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = DELAY_ORDER_QUEUE_NAME, durable = "true"),
            exchange = @Exchange(
                    name = DELAY_EXCHANGE_NAME,
                    delayed = "true"),
            key = DELAY_ORDER_KEY
    ))
    public void listenOrderDelayMessage(Long orderId) {
        // 1.查询订单
        Order order = orderService.getById(orderId);
        // 2.检测订单状态，判断是否已支付
        if (order == null || order.getStatus() != 1) {
            // 订单不存在或已经支付
            log.info("订单已支付：" + orderId);
            return;
        }
        // 3.未支付，需要查询支付流水状态
        PayOrderDTO payOrder = payClient.queryPayOrderByBizOrderNo(orderId);
        // 4.判断是否支付
        if (payOrder != null && payOrder.getStatus() == 3) {
            // 4.1.已支付，标记订单状态为已支付--一般不会发生，特殊情况：在向交换机发送更新订单状态时失败
            orderService.markOrderPaySuccess(orderId);
        } else {
            // 4.2.未支付，取消订单，恢复库存
            orderService.cancelOrder(orderId);
        }

    }
}
