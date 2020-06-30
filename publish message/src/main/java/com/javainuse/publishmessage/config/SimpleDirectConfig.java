package com.javainuse.publishmessage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleDirectConfig {

    @Value("${rabbitmq.queue}")
    String queueName;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    /*
        Exchanges are message routing agents, defined per virtual host within RabbitMQ.
        An exchange is responsible for the routing of the messages to the different queues.
        An exchange accepts messages from the producer application and routes them to message queues
        with help of header attributes, bindings, and routing keys.

        We will use a direct exchange instead. The routing algorithm behind a direct exchange
        is simple a message goes to the queues whose binding key exactly matches
        the routing key of the message.
     */
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

}
