package com.univ.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PersonReceiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonReceiver.class);


  @JmsListener(destination = "PERSON.PROCESSING.D")
  public void receive(String message) {
    LOGGER.info("received message='{}'", message);
  }
}
