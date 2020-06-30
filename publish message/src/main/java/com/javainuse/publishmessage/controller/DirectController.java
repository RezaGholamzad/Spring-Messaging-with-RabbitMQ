package com.javainuse.publishmessage.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectController {

    private final RabbitTemplate rabbitTemplate;

    public DirectController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /*
        localhost:8080/directProducer?exchangeName=direct-exchange&
        routingKey=admin&message=HelloWorld
     */
    @GetMapping("/directProducer")
    public String producer(@RequestParam("exchangeName") String exchange,
                           @RequestParam("routingKey") String routingKey,
                           @RequestParam("message") String message){

        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return "Message sent to the RabbitMQ Successfully";
    }
}
