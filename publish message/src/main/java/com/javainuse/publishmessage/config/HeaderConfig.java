package com.javainuse.publishmessage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfig {

    @Bean
    Queue marketingQueueHeaderExchange() {
        return new Queue("marketingQueueHeaderExchange", false);
    }

    @Bean
    Queue financeQueueHeaderExchange() {
        return new Queue("financeQueueHeaderExchange", false);
    }

    @Bean
    Queue adminQueueHeaderExchange() {
        return new Queue("adminQueueHeaderExchange", false);
    }

    /*
        In this type of exchange the routing queue is selected based on the criteria specified
        in the headers instead of the routing key. This is similar to topic exchange type,
        but here we can specify complex criteria for selecting routing queues.
     */
    @Bean
    HeadersExchange headersExchange(){
        return new HeadersExchange("header-exchange");
    }

    @Bean
    Binding marketingBindingHeaderExchange(Queue marketingQueueHeaderExchange,
                                           HeadersExchange headersExchange){
        return BindingBuilder.bind(marketingQueueHeaderExchange)
                .to(headersExchange)
                .where("department")
                .matches("marketing");
    }

    @Bean
    Binding financeBindingHeaderExchange(Queue financeQueueHeaderExchange,
                                         HeadersExchange headersExchange){
        return BindingBuilder.bind(financeQueueHeaderExchange)
                .to(headersExchange)
                .where("department")
                .matches("finance");
    }

    @Bean
    Binding adminBindingHeaderExchange(Queue adminQueueHeaderExchange,
                                       HeadersExchange headersExchange){
        return BindingBuilder.bind(adminQueueHeaderExchange)
                .to(headersExchange)
                .where("department")
                .matches("admin");
    }
}
