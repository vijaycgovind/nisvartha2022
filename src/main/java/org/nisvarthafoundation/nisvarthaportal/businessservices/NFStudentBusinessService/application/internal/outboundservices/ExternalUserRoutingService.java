package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.outboundservices;

import java.net.URI;
import java.net.URISyntaxException;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Notifications;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.interfaces.rest.tranform.StudentNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserRoutingService {

	Logger logger = LoggerFactory.getLogger(ExternalStudentRoutingService.class);


	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<String> UserNotificationServiceInvoke(String userName, String customerEmail, String operation ) {
		
		 
		 Notifications customerNotification = new Notifications();
			
		 
			customerNotification.setName(userName);
			customerNotification.setEmailId(customerEmail);
			customerNotification.setOperation(operation);
	 
	 String baseUrl = "http://localhost:9090/notification/sendmail";
 	// String baseUrl = env.getProperty("	");
		
		URI uri;
			try {
				uri = new URI(baseUrl);
				
				return(restTemplate.postForEntity(baseUrl, customerNotification, String.class));
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
	}
}
