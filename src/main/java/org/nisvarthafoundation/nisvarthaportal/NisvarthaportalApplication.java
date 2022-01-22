package org.nisvarthafoundation.nisvarthaportal;


import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NisvarthaportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NisvarthaportalApplication.class, args);
		
		final Logger LOGGER=LoggerFactory.getLogger(NisvarthaportalApplication.class);		
		LOGGER.info("<<---------------------------Nisvartha Portal Service Started----------------------------->>");
	}
	
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

}