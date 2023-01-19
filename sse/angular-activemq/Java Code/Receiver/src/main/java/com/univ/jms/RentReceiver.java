package com.univ.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class RentReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentReceiver.class);


	  @JmsListener(destination = "RENT.PROCESSING.D")
	  public void receive(String message) {
	    LOGGER.info("received message='{}'", message);
	  }
}
