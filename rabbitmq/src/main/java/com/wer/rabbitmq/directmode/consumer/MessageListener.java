package com.wer.rabbitmq.directmode.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者 :
 */
//@Component
public class MessageListener {

    @RabbitListener(queues = "directQueue1")
    public void fanoutListener1(String message) {
        System.out.println("路由模式监听器1：" + message);
    }

    @RabbitListener(queues = "directQueue2")
    public void fanoutListener2(String message) {
        System.out.println("路由模式监听器2：" + message);
    }

}
