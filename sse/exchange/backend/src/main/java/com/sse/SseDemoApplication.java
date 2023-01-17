package com.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SseDemoApplication {
private static Logger logger= LoggerFactory.getLogger (SseDemoApplication.class);

	public static void main(String[] args) {
		logger.info ("Starting application");
		SpringApplication.run(SseDemoApplication.class, args);
	}

}
