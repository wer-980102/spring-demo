package com.wer.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 简单模式、 工作模式、 发布与订阅模式、 路由模式、通配符模式、 远程调用模式（基本不会用到）
 *
 * 细节点：
 *  1.消费者的通道名称必须是已有通道名称
 */
@SpringBootApplication
public class RabbitmqApplication {

    public static void main(String[] args) {
        System.out.println("RabbitMq启动成功！");
        SpringApplication.run(RabbitmqApplication.class, args);
    }

}
