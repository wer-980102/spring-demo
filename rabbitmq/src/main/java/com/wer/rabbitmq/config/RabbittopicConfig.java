package com.wer.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通配符模式
 */
@Configuration
public class RabbittopicConfig {


    /**配置队列 **/
    @Bean
    public Queue topicQueue1() {
        return new Queue("topicQueue1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topicQueue2");
    }

    /** 配置交换器 **/
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /** 配置绑定 **/
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.*");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
    }

}
