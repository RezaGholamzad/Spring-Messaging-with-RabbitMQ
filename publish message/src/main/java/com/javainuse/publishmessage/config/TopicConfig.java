package com.javainuse.publishmessage.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    @Bean
    Queue marketingQueueTopicExchange() {
        return new Queue("marketingQueueTopicExchange", false);
    }

    @Bean
    Queue financeQueueTopicExchange() {
        return new Queue("financeQueueTopicExchange", false);
    }

    @Bean
    Queue adminQueueTopicExchange() {
        return new Queue("adminQueueTopicExchange", false);
    }

    @Bean
    Queue allQueueTopicExchange() {
        return new Queue("allQueueTopicExchange", false);
    }

    /*
        Here again the routing key is made use of. But unlike in direct exchange type,
        here the routing key of the exchange and the bound queues should not necessarily
        be an exact match. Using regular expressions like wildcard we can send
        the exchange to multiple bound queues.
     */
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding marketingBindingTopicExchange(Queue marketingQueueTopicExchange,
                                          TopicExchange topicExchange){
        return BindingBuilder.bind(marketingQueueTopicExchange)
                .to(topicExchange)
                .with("queue.marketing");
    }

    @Bean
    Binding financeBindingTopicExchange(Queue financeQueueTopicExchange,
                                        TopicExchange topicExchange){
        return BindingBuilder.bind(financeQueueTopicExchange)
                .to(topicExchange)
                .with("queue.finance");
    }

    @Bean
    Binding adminBindingTopicExchange(Queue adminQueueTopicExchange,
                                      TopicExchange topicExchange){
        return BindingBuilder.bind(adminQueueTopicExchange)
                .to(topicExchange)
                .with("queue.admin");
    }

    @Bean
    Binding allBindingTopicExchange(Queue allQueueTopicExchange,
                                    TopicExchange topicExchange){
        return BindingBuilder.bind(allQueueTopicExchange)
                .to(topicExchange)
                .with("queue.*");
    }
}
