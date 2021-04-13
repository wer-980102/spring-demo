package com.wer.rabbitmq.workmode.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 工作模式-消费者
 */
//@Component
public class MessageListener {

    @RabbitListener(queues = "workQueue")
    public void workListener1(String message) {
        System.out.println("工作模式监听器1：" + message);
    }

    @RabbitListener(queues = "workQueue")
    public void workListener2(String message) {
        System.out.println("工作模式监听器2：" + message);
    }

}
