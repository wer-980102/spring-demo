package com.wer.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由模式
 */
@Configuration
public class RabbitdirectConfig {

    /**配置交换机  directExchange：队列名称**/
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    /**配置队列  directQueue1：通道名称**/
    @Bean
    public Queue directQueue1() {
        return new Queue("directQueue1", true, false, false, null);
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("directQueue2", true, false, false, null);
    }
    /** 配置绑定 **/
    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("one");
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("two");
    }

}
