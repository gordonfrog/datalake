package com.univ.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String destination, Object object) {
		
		ObjectWriter ow = new ObjectMapper().writer();
		try {
			String message = ow.writeValueAsString(object);
			LOGGER.info("sending message='{}' to destination='{}'", message, destination);
			jmsTemplate.convertAndSend(destination, message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
