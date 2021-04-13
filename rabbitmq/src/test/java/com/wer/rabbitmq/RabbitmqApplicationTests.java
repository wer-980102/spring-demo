package com.wer.rabbitmq;

import com.wer.rabbitmq.directmode.producer.DirecProduct;
import com.wer.rabbitmq.fanoutmode.producer.FanoutProduct;
import com.wer.rabbitmq.simplemode.producer.ProducerProduct;
import com.wer.rabbitmq.topicmode.producer.TopicProduct;
import com.wer.rabbitmq.workmode.producer.WorkProduct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RabbitmqApplicationTests {

   // @Autowired
    private ProducerProduct producerProduct;
   // @Resource
    private WorkProduct workProduct;
    //@Autowired
    private FanoutProduct fanoutProduct;
   // @Autowired
    private DirecProduct direcProduct;
    @Autowired
    private TopicProduct topicProduct;

    @Test
    void contextLoads() {
        log.info("简单模式test!");
        producerProduct.simpleProduct();
    }

    @Test
    void contextLoads1() {
        log.info("工作模式test!");
        workProduct.workProduct();
    }

    @Test
    void contextLoads2() {
        log.info("发布者模式test!");
        fanoutProduct.fanoutProduct();
    }

    @Test
    void contextLoads3() {
        log.info("路由模式test!");
        direcProduct.directProduct1();
        direcProduct.directProduct2();
    }

    @Test
    void contextLoads4() {
        log.info("通配符模式test!");
        topicProduct.topicProduct();
    }
}
