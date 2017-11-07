package com.unimedsci.mq.spring;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConf {
    @Bean(name = "p1_message1")
    public Queue queueMessage1() {
        return new Queue("zilla.p1.message1", true);
    }

    @Bean(name = "p1_message2")
    public Queue queueMessage2() {
        return new Queue("zilla.p1.message2", true);
    }

    @Bean(name = "p1_messages")
    public Queue queueMessages() {
        return new Queue("zilla.p1.messages", true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("zilla");
    }

    @Bean
    Binding bindingExchangeMessage1(@Qualifier("p1_message1") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("zilla.device.message.message1");
    }

    @Bean
    Binding bindingExchangeMessage2(@Qualifier("p1_message2") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("zilla.device.message.message2");
    }

    @Bean
    Binding bindingExchangeMessages(@Qualifier("p1_messages") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("zilla.device.message.#");//*表示一个词,#表示零个或多个词
    }
}
