package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.outboundservices;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Mentor;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.MentorNotificationDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class ExternalMentorRoutingService {
	
	Logger logger = LoggerFactory.getLogger(ExternalStudentRoutingService.class);

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<String> MentorNotificationServiceInvoke(String mentorname, String customerEmail, String operation ) {
		
		 
		 Notifications customerNotification = new Notifications();
		 
		// customerNotification.setStudentNumber(stNumber.getStudentNumber());
		 customerNotification.setName(mentorname);
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
