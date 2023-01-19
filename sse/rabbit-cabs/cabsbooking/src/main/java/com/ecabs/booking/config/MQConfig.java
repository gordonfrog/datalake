package com.ecabs.booking.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
  author kamochejackson@gmail.com created on 1/26/20
*/
@Component
@ConfigurationProperties(prefix = "rabbit")
@Setter
@Getter
public class MQConfig {
    private String MessageExchange;
    private String MessageAuditQueue;
    private String BookingExchange;
    private String BookingAddQueue;
    private String BookingEditQueue;
    private String BookingDeleteQueue;

}
