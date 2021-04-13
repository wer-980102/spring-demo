package com.wer.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发布订阅模式
 */
@Configuration
public class RabbitfanoutConfig {

    /** 配置交换器   fanoutExchange：队列名**/
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    /**配置队列  fanoutQueue1：通道名**/
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutQueue2");
    }

    /**配置绑定**/
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

}
