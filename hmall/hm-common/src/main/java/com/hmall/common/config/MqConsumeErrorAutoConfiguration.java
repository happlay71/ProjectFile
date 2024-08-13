package com.hmall.common.config;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConsumeErrorAutoConfiguration {

    @Bean
    public Queue errorQueue() {

    }

    @Bean
    public Exchange errorExchange() {

    }
}
