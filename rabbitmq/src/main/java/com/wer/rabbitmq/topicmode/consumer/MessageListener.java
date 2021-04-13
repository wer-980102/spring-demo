package com.wer.rabbitmq.topicmode.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者 :
 */
@Component
public class MessageListener {

    @RabbitListener(queues = "topicQueue1")
    public void fanoutListener1(String message) {
        System.out.println("通配符监听器1：" + message);
    }

    @RabbitListener(queues = "topicQueue2")
    public void fanoutListener2(String message) {
        System.out.println("通配符监听器2：" + message);
    }

}
