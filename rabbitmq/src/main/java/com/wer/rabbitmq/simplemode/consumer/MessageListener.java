package com.wer.rabbitmq.simplemode.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
//@Component
public class MessageListener {

    @RabbitListener(queues = "werSimpleQueue")
    public void simpleListener(String message){
        System.out.println("简单模式监听器："+message);
    }

    @RabbitListener(queues = "myqueue")
    public void myqueue(String message){
        System.out.println("模式监听器："+message);
    }

}
