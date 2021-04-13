package com.wer.rabbitmq.directmode.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 路由模式
 */
//@Component
public class DirecProduct {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void directProduct1() {
        for (int num = 0; num < 5; num++) {
            rabbitTemplate.convertAndSend("directExchange","one", "发送到路由队列1消息"+num);
        }
    }
    public void directProduct2() {
        for (int num = 0; num < 5; num++) {
            rabbitTemplate.convertAndSend("directExchange","two", "发送到路由队列2消息"+num);
        }
    }

}
