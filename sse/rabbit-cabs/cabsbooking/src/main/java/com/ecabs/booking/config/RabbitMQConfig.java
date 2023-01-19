package com.ecabs.booking.config;

import lombok.var;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ecabs.booking.common.CommonUtils.*;

/*
  author kamochejackson@gmail.com created on 1/26/20
*/

@EnableRabbit
@Configuration
public class RabbitMQConfig  {

    private MQConfig mqConfig;

    public RabbitMQConfig(MQConfig mqConfig) {
        this.mqConfig = mqConfig;
    }

    @Bean
    TopicExchange getMessageExchange(){
        return new TopicExchange(mqConfig.getMessageExchange());
    }


    @Bean
    Queue getMessageAuditQueue(){
        return new Queue(mqConfig.getMessageAuditQueue());
    }

    @Bean
    Queue getBookingAddQueue(){
        return new Queue(mqConfig.getBookingAddQueue());
    }
    @Bean
    Queue getBookingEditQueue(){
        return new Queue(mqConfig.getBookingEditQueue());
    }
    @Bean
    Queue getBookingDeleteQueue(){
        return new Queue(mqConfig.getBookingDeleteQueue());
    }

    @Bean
    Binding declareMessageAuditBinding(){
        return BindingBuilder.bind(getMessageAuditQueue()).to(getMessageExchange()).with("messages.*");
    }
    @Bean
    DirectExchange getBookingExchange(){
        return new DirectExchange(mqConfig.getBookingExchange());
    }

    @Bean
    Binding declareBookingExchangeAndMessageExchangeBinding(){
        return BindingBuilder.bind(getBookingExchange()).to(getMessageExchange()).with("messages.*");
    }


    @Bean
    Binding declareBookingAddQueueBinding(){
        return BindingBuilder.bind(getBookingAddQueue()).to(getBookingExchange()).with(BOOKING_ADD_QUEUE_KEY);
    }

    @Bean
    Binding declareBookingEditQueueBinding(){
        return BindingBuilder.bind(getBookingEditQueue()).to(getBookingExchange()).with(BOOKING_EDIT_QUEUE_KEY);
    }


    @Bean
    Binding declareBookingDeleteQueueBinding(){
        return BindingBuilder.bind(getBookingDeleteQueue()).to(getBookingExchange()).with(BOOKING_DELETE_QUEUE_KEY);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
//
//        return mapper;
//    }
}
