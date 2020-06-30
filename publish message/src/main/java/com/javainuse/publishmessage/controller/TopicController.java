package com.javainuse.publishmessage.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    private final RabbitTemplate rabbitTemplate;

    public TopicController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /*
        localhost:8080/topicProducer?exchangeName=topic-exchange&
        routingKey=queue.admin&message=HelloWorld
     */
    @GetMapping("/topicProducer")
    public String producer(@RequestParam("exchangeName") String exchange,
                           @RequestParam("routingKey") String routingKey,
                           @RequestParam("message") String message){

        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return "Message sent to the RabbitMQ Topic Exchange Successfully";
    }
}
