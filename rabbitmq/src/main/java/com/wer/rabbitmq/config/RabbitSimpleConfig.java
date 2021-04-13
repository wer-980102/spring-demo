package com.wer.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;

/**
 * 设置通道
 */
@Configuration
public class RabbitSimpleConfig {

    public static final String SIMPLE_QUEUE = "werSimpleQueue";
    @Bean
    public Queue simpleQueue(){
        System.out.println("RabbitMQ-链接成功！");
        return new Queue(SIMPLE_QUEUE,true);
    }
}
