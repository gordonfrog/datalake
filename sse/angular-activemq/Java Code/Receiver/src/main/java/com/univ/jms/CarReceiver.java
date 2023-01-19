package com.univ.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CarReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarReceiver.class);


	  @JmsListener(destination = "CAR.PROCESSING.D")
	  public void receive(String message) {
	    LOGGER.info("received message='{}'", message);
	  }
}
