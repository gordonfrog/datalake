package com.coffeeandcode.amqp.consumerapi;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MachineConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = MachineAMQPConfig.QUEUE)
    // Listening Queues and do somenthing when messages appear
    public void consumer(Message message) {
    	// Send Message to Websocker Broker
        simpMessagingTemplate.convertAndSend(MachineWebSocketConfiguration.BROKER,
                new String(message.getBody()));
    }
}
