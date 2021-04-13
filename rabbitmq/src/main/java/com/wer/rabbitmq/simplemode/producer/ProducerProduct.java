package com.wer.rabbitmq.simplemode.producer;

import com.wer.rabbitmq.RabbitmqApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * 默认推送数据不完整-轮训推送（）
 */
@Component
public class ProducerProduct {

    @Autowired
    RabbitTemplate rabbitTemplate;


    public void simpleProduct(){
        for (int num = 0; num < 20; num++) {
            rabbitTemplate.convertAndSend("werSimpleQueue", "简单模式"+num);
        }
    }
}
