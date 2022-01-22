package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.commandservices;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.outboundservices.ExternalUserRoutingService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.rules.EventType;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Events;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Mentor;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;


@Service
public class AdminMentorCommandService {
	
	@Autowired
	MongoTemplate mongoTemplate;

	
	public void mentorSetup(Mentor adminMentorData) {
		
		System.out.print("Inside MentorSetup in Admin Mentor Command Service");

		adminMentorData.setStatus("active");
		adminMentorData.setUpdatedBy(adminMentorData.getUpdatedBy());
		adminMentorData.setUpdatedDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
	     mongoTemplate.save(adminMentorData, "mentors");
	     
     	Events mentorCreateEvent = new Events();
     	mentorCreateEvent.setNisarthaID(adminMentorData.getNisvarthamentorId());
     	// setid(adminMentorData.getNisvarthamentorId());
     	mentorCreateEvent.setCreatedBy(adminMentorData.getUpdatedBy());
     	mentorCreateEvent.setCreatedDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
     	mentorCreateEvent.setEventID(EventType.MENTORCREATED_ID);
     	mentorCreateEvent.setDomain(EventType.DOMAIN_ADMINISTRATION);
     	mentorCreateEvent.setDomainEventType(EventType.MENTORCREATED);
     	mentorCreateEvent.setPayload(adminMentorData.toString());
        
    	mongoTemplate.save(mentorCreateEvent, "nivarthaEventStore");
    	
    	Date date = Calendar.getInstance().getTime();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	String strDate = dateFormat.format(date);
			Events mentorUpdateEvent = new Events();

			mentorUpdateEvent.setNisarthaID(adminMentorData.getNisvarthamentorId());
			//set_id(mentorId);
			mentorUpdateEvent.setCreatedBy("ADMIN >> TBC");
			mentorUpdateEvent.setCreatedDate(strDate);
			mentorUpdateEvent.setEventID(EventType.MENTORCREATED_ID);
			mentorUpdateEvent.setDomain(EventType.DOMAIN_ADMINISTRATION);
			mentorUpdateEvent.setDomainEventType(EventType.MENTORCREATED);
			mentorUpdateEvent.setPayload(adminMentorData.toString());
        
    	mongoTemplate.save(mentorUpdateEvent, "nivarthaEventStore");

	}
	
	public Boolean  mentorUpdate(Mentor adminMentorData, String mentorId) {
		
		Boolean  userUpdate = false;
		
		Date date = Calendar.getInstance().getTime();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	String strDate = dateFormat.format(date);
    	
	     Query query = new Query();
	     query.addCriteria(Criteria.where("nisvarthamentorId").is(mentorId));
	     Update update = new Update();
	     mongoTemplate.find(query, Mentor.class);
	     update.set("mentorname", adminMentorData.getNisvarthamentorId());
	     update.set("email", adminMentorData.getEmail());
	     update.set("gender", adminMentorData.getGender());
	     update.set("mobilePhone", adminMentorData.getMentorname());
	     update.set("homePhone", adminMentorData.getHomePhone());
	     update.set("city", adminMentorData.getCity());
	     update.set("district", adminMentorData.getDistrict());
	     update.set("state", adminMentorData.getState());
	     update.set("status", adminMentorData.getStatus());
	     update.set("updatedBy", adminMentorData.getUpdatedBy());
	     update.set("updatedDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
         UpdateResult writeResult = mongoTemplate.updateMulti(query, update, "students");


 		
         if(writeResult.getModifiedCount()!=0) {
         	if(writeResult.getMatchedCount()!=0) {
 				userUpdate = true;
 				Events mentorUpdateEvent = new Events();

 				mentorUpdateEvent.setNisarthaID(mentorId);
 				//set_id(mentorId);
 				mentorUpdateEvent.setCreatedBy(mentorId);
 				mentorUpdateEvent.setCreatedDate(strDate);
 				mentorUpdateEvent.setEventID(EventType.MENTORUPDATED_ID);
 				mentorUpdateEvent.setDomain(EventType.DOMAIN_ADMINISTRATION);
 				mentorUpdateEvent.setDomainEventType(EventType.MENTORUPDATED);
 				mentorUpdateEvent.setPayload(adminMentorData.toString());
		        
		    	mongoTemplate.save(mentorUpdateEvent, "nivarthaEventStore");
	
 	
 /********
  * 
  * Sending Notification mail to Student 
  * 	
  */
 			       
 			       String userName = adminMentorData.getMentorname();
 			       //String userEmail = userCredentials.getEmail();
 			       String customerEmail = "champaka.tn@gmail.com";
 			       String operation = "UserCreated";
 			       
 		//	       ExternalUserRoutingService.UserNotificationServiceInvoke(userName, customerEmail, operation);
 			 return userUpdate;
         	}
         }
         return userUpdate;
		
	}
	
public ResponseEntity<HttpStatus> deleteMentorbyID(String nisvarthamentorId ) {
	
		try {
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String strDate = dateFormat.format(date);

	
	        Query query = new Query();
	        query.addCriteria(Criteria.where("nisvarthamentorId").is(nisvarthamentorId));
            mongoTemplate.findAndRemove(query, Mentor.class, "mentors");
            
            	    Events mentorDeleteEvent = new Events();
    		        
    				mentorDeleteEvent.setNisarthaID(nisvarthamentorId);
    				//set_id(nisvarthamentorId);
    				mentorDeleteEvent.setCreatedBy(nisvarthamentorId);
    				mentorDeleteEvent.setCreatedDate(strDate);
    				mentorDeleteEvent.setEventID(EventType.MENTORDELETED_ID);
    				mentorDeleteEvent.setDomain(EventType.DOMAIN_ADMINISTRATION);
    				mentorDeleteEvent.setDomainEventType(EventType.MENTORDELETED);
    				mentorDeleteEvent.setPayload(nisvarthamentorId+ "Mentor is deleted by " + nisvarthamentorId);
    		        
    		    	mongoTemplate.save(mentorDeleteEvent, "nivarthaEventStore");
    		    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	            
				}
				catch (Exception e) {
				    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				  }

	}	
	
}
