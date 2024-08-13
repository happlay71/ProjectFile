package com.hmall.common.utils;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

@RequiredArgsConstructor
public class CustomMPPUtils implements MessagePostProcessor {
    private final Integer delayTime; // 以毫秒为单位的延迟时间

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setDelay(delayTime);
        return message;
    }
}
