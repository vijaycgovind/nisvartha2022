package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.application.internal.outboundservices;



import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.valueobjects.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

//import jdk.internal.jline.internal.Log;

@Service
public class MentorAPIService {
	
	@Autowired
	RestTemplate restTemplate;

	
	@SuppressWarnings("unused")
	public void schduledQuarterlyRemindersToMentors() {
	
		String baseUrl = "http://localhost:9090/student/mentorEmailAddress";
		String notificationUrl = "http://localhost:9090/notification/mentorNotification";

		
		try {


	         Mentor theMentor = new Mentor();
				
			ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
			
			HttpStatus statusCode = response.getStatusCode();
		
			System.out.println("status code >>>>>>> " +statusCode);
			
			if(statusCode.equals(HttpStatus.OK))
			{
			String jsonString = response.getBody();
			ObjectMapper theObjectMapper = new ObjectMapper();
			System.out.println("value as string >>>>>> : "+jsonString);
			
			//build the required data for email notification for pending forms
			
			ObjectMapper mapper = new ObjectMapper();
			Mentor[] theMentorJSONObject = mapper.readValue(jsonString, Mentor[].class);
		
			System.out.println("OBJECT CONVERTED>>>>>:: "+theMentorJSONObject.toString());
			
			for (Mentor itr : theMentorJSONObject) {

				System.out.println("OBJECT inside loop >>>>>:: "+itr.getMentorname());
				System.out.println("OBJECT inside loop >>>>>:: "+itr.getEmail());
				//System.out.println(itr);
				//send notification to mentors
			    restTemplate.postForObject(notificationUrl, itr, String.class);
			    
			}
			
		
			
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
