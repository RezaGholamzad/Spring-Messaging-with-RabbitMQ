package com.javainuse.publishmessage.controller;

import com.javainuse.publishmessage.domain.Employee;
import com.javainuse.publishmessage.service.SimpleDirectSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleDirectController {

    private final SimpleDirectSender simpleDirectSender;

    public SimpleDirectController(SimpleDirectSender simpleDirectSender) {
        this.simpleDirectSender = simpleDirectSender;
    }

    @GetMapping(value = "/simpleDirectProducer")
    public String producer(@RequestParam String name) {

        Employee emp=new Employee();
        emp.setName(name);
        simpleDirectSender.send(emp);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}
