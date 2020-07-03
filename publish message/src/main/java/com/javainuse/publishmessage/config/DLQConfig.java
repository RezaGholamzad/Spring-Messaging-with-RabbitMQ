package com.javainuse.publishmessage.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    A dead-letter queue (DLQ), sometimes which is also known as an undelivered-message queue,
    is a holding queue for messages that cannot be delivered to their destinations due to some reason.
    In message queueing the dead letter queue is a service implementation to
    store messages that meet one or more of the following failure criteria:

    + Message that is sent to a queue that does not exist.
    + Queue length limit exceeded.
    + Message length limit exceeded.
    + Message is rejected by another queue exchange.
    + Message reaches a threshold read counter number, because it is not consumed.
      Sometimes this is called a "back out queue".

 */
@Configuration
public class DLQConfig {

    @Bean
    DirectExchange deadLetterExchange(){
        return new DirectExchange("deadLetterExchange");
    }

    @Bean
    DirectExchange employeeDirectExchange(){
        return new DirectExchange("employeeExchange");
    }

    @Bean
    Queue deadLetterQueue(){
        return QueueBuilder.durable("deadLetter.queue").build();
    }

    /*
            For the employeeQueue queue specify the x-dead-letter-exchange argument as the deadLetterExchange.
            This means that any message in employeeQueue queue that cannot be delivered will be sent to
            the deadLetterExchange.
         */
    @Bean
    Queue employeeQueue(){
        return QueueBuilder.durable("employee.queue")
                .deadLetterExchange("deadLetterExchange")
                .deadLetterRoutingKey("deadLetter")
//                .ttl(30000) // It then automatically transfers to the dead letter queue for three seconds
                .build();
    }

    @Bean
    Binding deadLetterBinding(){
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with("deadLetter");
    }

    @Bean
    Binding employeeBinding(){
        return BindingBuilder.bind(employeeQueue())
                .to(employeeDirectExchange())
                .with("employee");
    }

}
