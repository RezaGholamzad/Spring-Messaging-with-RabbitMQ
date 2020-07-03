package com.javainuse.consumemessage.exception;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

@Component
class RabbitRetryHandler implements RabbitListenerErrorHandler {

    private static final byte REDELIVER_NUMBER = 5;
    private static byte counter = 1;

    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message,
                              ListenerExecutionFailedException exception) throws Exception {
        System.out.println("Redelivered: " + amqpMessage.getMessageProperties().isRedelivered());
        System.out.println("Redelivered: " + counter);
        if (counter > REDELIVER_NUMBER) {
            counter = 1;
            throw new AmqpRejectAndDontRequeueException(exception);
        }
        else {
            counter++;
            throw new InvalidNameException();
        }
    }

}
