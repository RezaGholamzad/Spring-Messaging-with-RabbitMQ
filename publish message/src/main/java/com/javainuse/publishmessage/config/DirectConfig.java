package com.javainuse.publishmessage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    @Bean
    Queue marketingQueueDirectExchange() {
        return new Queue("marketingQueueDirectExchange", false);
    }

    @Bean
    Queue financeQueueDirectExchange() {
        return new Queue("financeQueueDirectExchange", false);
    }

    @Bean
    Queue adminQueueDirectExchange() {
        return new Queue("adminQueueDirectExchange", false);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("direct-exchange");
    }

    @Bean
    Binding marketingBindingDirectExchange(Queue marketingQueueDirectExchange,
                                           DirectExchange directExchange){
        return BindingBuilder.bind(marketingQueueDirectExchange)
                .to(directExchange)
                .with("marketing");
    }

    @Bean
    Binding financeBindingDirectExchange(Queue financeQueueDirectExchange,
                                         DirectExchange directExchange){
        return BindingBuilder.bind(financeQueueDirectExchange)
                .to(directExchange)
                .with("finance");
    }

    @Bean
    Binding adminBindingDirectExchange(Queue adminQueueDirectExchange,
                                       DirectExchange directExchange){
        return BindingBuilder.bind(adminQueueDirectExchange)
                .to(directExchange)
                .with("admin");
    }
}
