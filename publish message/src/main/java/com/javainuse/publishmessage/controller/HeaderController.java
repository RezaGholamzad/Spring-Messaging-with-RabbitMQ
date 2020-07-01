package com.javainuse.publishmessage.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    private final RabbitTemplate rabbitTemplate;

    public HeaderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /*
        localhost:8080/headerProducer?exchangeName=header-exchange&
        department=admin&message=HelloWorld
     */
    @GetMapping("/headerProducer")
    public String producer(@RequestParam("exchangeName") String exchange,
                           @RequestParam("department") String department,
                           @RequestParam("message") String messageData){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("department", department);
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage(messageData, messageProperties);
        rabbitTemplate.send(exchange, "", message);
        return "Message sent to the RabbitMQ Header Exchange Successfully";
    }
}
