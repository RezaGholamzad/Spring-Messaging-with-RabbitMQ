package com.javainuse.publishmessage.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanoutController {

    private final RabbitTemplate rabbitTemplate;

    public FanoutController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /*
        localhost:8080/fanoutProducer?exchangeName=fanout-exchange&message=HelloWorld
    */
    @GetMapping("/fanoutProducer")
    public String producer(@RequestParam("exchangeName") String exchange,
                           @RequestParam("message") String message){

        rabbitTemplate.convertAndSend(exchange, "", message);
        return "Message sent to the RabbitMQ Fanout Exchange Successfully";
    }
}
