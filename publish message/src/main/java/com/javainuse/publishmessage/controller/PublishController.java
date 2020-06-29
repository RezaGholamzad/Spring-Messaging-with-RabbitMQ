package com.javainuse.publishmessage.controller;

import com.javainuse.publishmessage.domain.Employee;
import com.javainuse.publishmessage.service.RabbitMQSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    private final RabbitMQSender rabbitMQSender;

    public PublishController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam String name) {

        Employee emp=new Employee();
        emp.setName(name);
        rabbitMQSender.send(emp);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}
