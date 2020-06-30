package com.javainuse.publishmessage.service;

import com.javainuse.publishmessage.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SimpleDirectSender {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleDirectSender.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    public SimpleDirectSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Employee employee) {
        rabbitTemplate.convertAndSend(exchange, routingKey, employee);
        LOG.info("Send msg > " + employee);
    }
}
