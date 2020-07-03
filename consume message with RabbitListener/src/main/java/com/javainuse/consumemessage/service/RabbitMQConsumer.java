package com.javainuse.consumemessage.service;

import com.javainuse.consumemessage.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receivedMessage(Employee employee) {
        LOG.info("Received Message From RabbitMQ: " + employee);
    }

//    ######################## test DLQ #############################
    @RabbitListener(queues = "employee.queue", errorHandler = "rabbitRetryHandler")
    public void getEmployee(Employee employee) {
        LOG.info("Received Employee From RabbitMQ: " + employee);
        if (!employee.getName().matches("[A-Z][a-z]*")){
            throw new RuntimeException();
        }
    }
}
