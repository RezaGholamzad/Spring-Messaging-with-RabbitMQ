package com.javainuse.publishmessage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    @Bean
    Queue marketingQueueFanoutExchange() {
        return new Queue("marketingQueueFanoutExchange", false);
    }

    @Bean
    Queue financeQueueFanoutExchange() {
        return new Queue("financeQueueFanoutExchange", false);
    }

    @Bean
    Queue adminQueueFanoutExchange() {
        return new Queue("adminQueueFanoutExchange", false);
    }

    /*
        The message is routed to all the available bounded queues.
        The routing key if provided is completely ignored.
        So this is a kind of publish-subscribe design pattern.
     */
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    Binding marketingBindingFanoutExchange(Queue marketingQueueFanoutExchange,
                                           FanoutExchange fanoutExchange){
        return BindingBuilder.bind(marketingQueueFanoutExchange)
                .to(fanoutExchange);
    }

    @Bean
    Binding financeBindingFanoutExchange(Queue financeQueueFanoutExchange,
                                           FanoutExchange fanoutExchange){
        return BindingBuilder.bind(financeQueueFanoutExchange)
                .to(fanoutExchange);
    }

    @Bean
    Binding adminBindingFanoutExchange(Queue adminQueueFanoutExchange,
                                       FanoutExchange fanoutExchange){
        return BindingBuilder.bind(adminQueueFanoutExchange)
                .to(fanoutExchange);
    }
}
