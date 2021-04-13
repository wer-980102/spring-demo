package com.wer.rabbitmq.topicmode.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 */
@Component
public class TopicProduct {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void topicProduct() {
        rabbitTemplate.convertAndSend("topicExchange","topic.1111", "routkey为topic.one的消息");
      //  rabbitTemplate.convertAndSend("topicExchange","topic.one.two", "routkey为topic.one.two的消息");
    }

}
