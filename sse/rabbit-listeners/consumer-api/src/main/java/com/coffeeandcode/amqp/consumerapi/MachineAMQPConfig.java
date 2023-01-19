package com.coffeeandcode.amqp.consumerapi;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MachineAMQPConfig {

    public static final String QUEUE = "machines-created";
    public static final String EXCHANGE_NAME = "Machines";
    public static final String ROUTING_KEY = "";

    @Bean
    //  create exchange if not exists
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    // create a Queue to Bind exchange
    public Queue declareQueue() {
        return QueueBuilder.durable(QUEUE)
                .build();
    }

    @Bean
    // Create a Binding to link Exchange to Queue - Note that Exchange is Direct ( without filtering messages)
    public Binding declareBinding(Exchange exchange, Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY)
                .noargs();
    }
}
