package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.outboundservices;

import java.net.URI;

import java.net.URISyntaxException;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;
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
public class ExternalStudentRoutingService {

	static Logger logger = LoggerFactory.getLogger(ExternalStudentRoutingService.class);

	
	public ResponseEntity<String> studentNotificationServiceInvoke(String Name, String studentNumber, String customerEmail, String operation ) {
		
		RestTemplate restTemplate = new RestTemplate();
		 Notifications customerNotification = new Notifications();
		
		 
				customerNotification.setStudentNumber(studentNumber);
				customerNotification.setName(Name);
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
