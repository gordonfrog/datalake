package com.univ.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class VanReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(VanReceiver.class);


	  @JmsListener(destination = "VAN.PROCESSING.D")
	  public void receive(String message) {
	    LOGGER.info("received message='{}'", message);
	  }
}
