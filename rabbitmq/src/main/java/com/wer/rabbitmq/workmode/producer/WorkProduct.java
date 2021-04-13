package com.wer.rabbitmq.workmode.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工作模式-生产者
 */
//@Component
public class WorkProduct {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void workProduct(){
        for (int num = 0; num < 20; num++) {
            rabbitTemplate.convertAndSend("workQueue", "工作模式"+num);
        }
    }
}
