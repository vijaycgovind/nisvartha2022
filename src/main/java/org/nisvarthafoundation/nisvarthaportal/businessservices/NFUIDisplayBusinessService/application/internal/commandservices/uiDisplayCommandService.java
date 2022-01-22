package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.application.internal.commandservices;

import java.util.HashMap;
import java.util.Map;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.mentorstudent;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.operations.commands.UIDisplayAdminCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;



public class uiDisplayCommandService {
	

	@Autowired
	MongoTemplate mongoTemplate;

	public void mentorAssign(UIDisplayAdminCommand uiDisplayAdminCommand) {
		
		System.out.print("Inside mentorAssignSetup in Command Service");
	   
   
	        	        
	        mentorstudent mentStudent = new mentorstudent();
	        
	        mentStudent.setMentorName(uiDisplayAdminCommand.getMentor().getFirstName());
	        mentStudent.setStudentName(uiDisplayAdminCommand.getStData().getPersonalDetails().getStudentName());
	        
	        Map<String, Object> documentMap = new HashMap<String, Object>();
	        documentMap.put("msID", mentStudent);
	       
	        
	       // collection.insert(new BasicDBObject(documentMap));
	        
	        
	        mongoTemplate.save(mentStudent);
	        
	      //  return new StudentNumber(studentNumber1);
	    }
	}
