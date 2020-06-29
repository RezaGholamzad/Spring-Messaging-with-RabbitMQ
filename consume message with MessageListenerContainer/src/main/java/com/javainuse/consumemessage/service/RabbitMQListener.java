package com.javainuse.consumemessage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQListener.class);

    @Override
    public void onMessage(Message message) {
        LOG.info("Consuming Message - " + new String(message.getBody()));
    }
}
