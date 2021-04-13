package com.wer.rabbitmq.fanoutmode.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者 :
 */
//@Component
public class MessageListener {

    @RabbitListener(queues = "fanoutQueue1")
    public void fanoutListener1(String message) {
        System.out.println("发布订阅监听器1：" + message);
    }

    @RabbitListener(queues = "fanoutQueue2")
    public void fanoutListener2(String message) {
        System.out.println("发布订阅监听器2：" + message);
    }

}
