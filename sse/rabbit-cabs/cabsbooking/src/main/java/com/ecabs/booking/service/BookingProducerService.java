package com.ecabs.booking.service;

import com.ecabs.booking.config.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.ecabs.booking.common.CommonUtils.MESSAGE_EXCHANGE;

/*
  author kamochejackson@gmail.com created on 1/26/20
*/
@Service
@Slf4j
public class BookingProducerService {

    private RabbitTemplate rabbitTemplate;
    private MQConfig mqConfig;

    public BookingProducerService(RabbitTemplate rabbitTemplate, MQConfig mqConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.mqConfig = mqConfig;
    }

    /**
     * @param routingKey
     * @param data
     */
    public void sendMessage(String routingKey, Object data) {
        try {
            rabbitTemplate.convertAndSend(mqConfig.getMessageExchange(), routingKey, data);
        }catch (Exception e){
            log.error("BookingProducerService ERROR routing key :: {} :: {}",routingKey, e.getMessage());
        }
    }
}
