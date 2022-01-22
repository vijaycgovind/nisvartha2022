package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.application.internal.outboundservices;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.aggregates.ApplicationForm;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.entities.User;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.valueobjects.candidatePendingFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.databind.ObjectMapper;

//import jdk.internal.jline.internal.Log;

@Service
public class RegistrationAPIService {
	@Autowired
	RestTemplate restTemplate;

	@SuppressWarnings("unused")
	public void schduledQuarterlyRemindersToCandidates() {
		
	System.out.println("Iside >>>>>>>>> invoke");
	String baseUrl = "http://localhost:9090/Registration/NisvarthaStudent/pendingForms";
	String notificationUrl = "http://localhost:9090/notification/pendingFormNotification";

	
	try {


    	candidatePendingFormDTO theCandidatePendingFormDTO = new candidatePendingFormDTO();
			
		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
		
		HttpStatus statusCode = response.getStatusCode();
		//HttpStatus emailId = response.getBody();
		System.out.println("status code >>>>>>> " +statusCode);
		
		if(statusCode.equals(HttpStatus.OK))
		{
		String jsonString = response.getBody();
		ObjectMapper theObjectMapper = new ObjectMapper();
		System.out.println("value as string ??????????"+jsonString);
		
		//build the required data for email notification for pending forms
		
		ObjectMapper mapper = new ObjectMapper();
		ApplicationForm[] theApplicationFormJsonObj = mapper.readValue(jsonString, ApplicationForm[].class);
		//ApplicationForm theApplicationForm = mapper.readValue(jsonString, ApplicationForm.class);
		System.out.println("OBJECT CONVERTED"+theApplicationFormJsonObj.toString());
		
		for (ApplicationForm itr : theApplicationFormJsonObj) {

		    theCandidatePendingFormDTO.setNisvarthaTANId(itr.getApplicationFormNumber());
		    theCandidatePendingFormDTO.setCandidateEmailId(itr.getApplicationform().getPersonalDetails().getContact().getEmailId());
		    theCandidatePendingFormDTO.setBplcardProofFileStatus(itr.getApplicationform().getBplcardProofSubmitted());
		    theCandidatePendingFormDTO.setCasteProofFileStatus(itr.getApplicationform().getCasteProofSubmitted());
		    theCandidatePendingFormDTO.setHousephotoProofFileStatus(itr.getApplicationform().getHousephotoProofSubmitted());
		    theCandidatePendingFormDTO.setIncomeProofFileStatus(itr.getApplicationform().getIncomeProofSubmitted());
		    theCandidatePendingFormDTO.setMarkscardProofFileStatus(itr.getApplicationform().getMarkscardProofSubmitted());
		    theCandidatePendingFormDTO.setParentwriteupProofFileStatus(itr.getApplicationform().getParentwriteupProofSubmitted());
		    theCandidatePendingFormDTO.setPassbookProofFileStatus(itr.getApplicationform().getPassbookProofSubmitted());
		    theCandidatePendingFormDTO.setSignedapplicationformProofFileStatus(itr.getApplicationform().getSignedapplicationformProofSubmitted());
		    theCandidatePendingFormDTO.setStudentwriteupProofFileStatus(itr.getApplicationform().getStudentwriteupProofSubmitted());
		    
		   // System.out.println(theCandidatePendingFormDTO.toString());
		    
		    restTemplate.postForObject(notificationUrl, theCandidatePendingFormDTO, String.class);
		    
		}
		
		//Send email details to notificaiton service
		
		
		
		}
		else
		{
			//Log.info("Something went wrong while processing. Please Verify");
		}

	
		
	}
	 catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	}
	

}
