package com.wer.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 工作模式
 */
@Configuration
public class RabbitWorkConfig {
    public static final String WORK_QUEUE = "workQueue";
    @Bean
    public Queue workQueue(){
        return new Queue(WORK_QUEUE,true);
    }
}
