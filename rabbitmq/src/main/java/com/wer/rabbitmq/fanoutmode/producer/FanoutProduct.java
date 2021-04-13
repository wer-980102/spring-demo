package com.wer.rabbitmq.fanoutmode.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
//@Component
public class FanoutProduct {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void fanoutProduct(){
        for (int num = 0; num < 20; num++) {
            rabbitTemplate.convertAndSend( "fanoutExchange","","发布者模式"+num);
        }
    }
}
