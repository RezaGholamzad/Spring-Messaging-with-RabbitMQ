package com.javainuse.publishmessage.controller;

import com.javainuse.publishmessage.domain.Employee;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DLQController {

    private final RabbitTemplate rabbitTemplate;

    public DLQController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /*
        localhost:8080/dlqProducer?name=reza2
     */
    @GetMapping("/dlqProducer")
    public String dlqProducer(@RequestParam("name") String name){
        Employee employee = new Employee();
        employee.setName(name);
        rabbitTemplate.convertAndSend("employeeExchange",
                "employee", employee);
        return "Message sent to the RabbitMQ Successfully";
    }
}
