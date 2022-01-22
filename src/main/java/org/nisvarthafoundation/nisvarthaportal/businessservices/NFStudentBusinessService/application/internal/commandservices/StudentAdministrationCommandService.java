package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.commandservices;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.Candidate;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.rules.EventType;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Events;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;

@Service
public class StudentAdministrationCommandService{
	
	
  @Autowired
	MongoTemplate mongoTemplate;  
  /*****
    public boolean saveStudentRecord(AdministrationUserCommand administrationUserCommand){ 
    	
    	Boolean studentcreated = false;
    	
    	StudentStatus studentStatus = new StudentStatus();
    	
    	String nfStatus = "APPROVE";
    	String currentStatus = "APPROVE";
    	String updatedBy = "admin";
    	
    	
        Date date = Calendar.getInstance().getTime();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	    String updatedDate = dateFormat.format(date);
    	
	   studentStatus.setNfStatus(nfStatus);
	   studentStatus.setCurrentStatus(currentStatus);
	   studentStatus.setUpdatedBy(updatedBy);
	   studentStatus.setUpdatedDate(updatedDate);
	   
	   String test = "NFPAN"+RandomStringUtils.random(5, "ILOVEKARNATAKANISVARTHAINDIA$#1234567890");
	   administrationUserCommand.setNisvarthastudentid(test);
	   administrationUserCommand.setStudentStatus(studentStatus);
	   
	  // administrationUserCommand.set
    	// logic of creating stuent ID t b
    	NisvarthaRecord nisvarthastudentrecord = new NisvarthaRecord(administrationUserCommand.getApplicationformnumber(),
    			administrationUserCommand.getNisvarthastudentid(),
    			administrationUserCommand.getNisvarthastudentrecord(),
    			administrationUserCommand.getNisvarthaStudentDocumentRecord(),
    			administrationUserCommand.getDocumentStatus(),
    			administrationUserCommand.getNisvarthamentorrecord(),
    			administrationUserCommand.getNisvarthafinancerecord(),
    			administrationUserCommand.getSponsorshiprecord(), administrationUserCommand.getStudentStatus()

    			);    	
       
       if(nisvarthastudentrecord.getNisvarthastudentid() != null) {
			  
    	      String studentNumber = nisvarthastudentrecord.getNisvarthastudentid();
    	      String studentName = nisvarthastudentrecord.getNisvarthastudentrecord().getStudentname();
		      String studentEmail = nisvarthastudentrecord.getNisvarthastudentrecord().getEmailid();
		      String studentOperation = "Student Approved";
		        
		//	ExternalStudentRoutingService.studentNotificationServiceInvoke(studentName, studentNumber, studentEmail, studentOperation);
			return studentcreated;
       }
       return studentcreated;
     }
  
    
    
    //Update Student by ID usecase
	public Boolean StudentUpdatebyID(AdministrationUserCommand adminUserCommand ) {
		
		Boolean studentUpdate = false;
		System.out.print("Inside Nisvartharecord update in admin Command Service" +adminUserCommand.toString());
		   
		
			NisvarthaRecord newnisvarthaRecord = new NisvarthaRecord();
			newnisvarthaRecord.setApplicationformnumber(adminUserCommand.getApplicationformnumber());
			newnisvarthaRecord.setNisvarthastudentid(adminUserCommand.getNisvarthastudentid());
			newnisvarthaRecord.setNisvarthastudentrecord(adminUserCommand.getNisvarthastudentrecord());
			newnisvarthaRecord.setNisvarthafinancerecord(adminUserCommand.getNisvarthafinancerecord());
			newnisvarthaRecord.setNisvarthamentorrecord(adminUserCommand.getNisvarthamentorrecord());
			newnisvarthaRecord.setSponsorshiprecord(adminUserCommand.getSponsorshiprecord());
			
	 
	        Query query = new Query();
	        query.addCriteria(Criteria.where("nisvarthastudentid").is(adminUserCommand.getNisvarthastudentid()));
	        System.err.println("QUERY criteria ******** " +query.toString());
	        Update update = new Update();
	        
	        update.set("nisvarthastudentrecord", adminUserCommand.getNisvarthastudentrecord());
	        update.set("nisvarthafinancerecord", adminUserCommand.getNisvarthafinancerecord());
	        update.set("nisvarthamentorrecord", adminUserCommand.getNisvarthamentorrecord());
	        update.set("sponsorshiprecord", adminUserCommand.getSponsorshiprecord());
	        
	        System.out.println("Upserting in Mongo DB ");
	  
            UpdateResult writeResult = mongoTemplate.updateMulti(query, update, "students");
	        
	        System.out.println("UpdateResult" +writeResult);
	        
	        Date date = Calendar.getInstance().getTime();
 	    	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
 	    	String strDate = dateFormat.format(date);
	
	                if(writeResult.getModifiedCount()!=0) {
	        if(writeResult.getMatchedCount()!=0) {
	        	studentUpdate = true;
	        	
	        	
	        Events studentUpdateEvent = new Events();
	        studentUpdateEvent.setNisarthaID(adminUserCommand.getNisvarthastudentid());
	        studentUpdateEvent.setCreatedBy("Hard coded value ATM");
	        studentUpdateEvent.setCreatedDate(strDate);
	        studentUpdateEvent.setEventID(EventType.STUDENTUPDATEDEVENTID);
	        studentUpdateEvent.setDomain(EventType.DOMAIN_STUDENTDOMAIN);
	        studentUpdateEvent.setDomainEventType(EventType.STUDENTUPDATED);
	        studentUpdateEvent.setPayload(adminUserCommand.toString());
	        
	    	mongoTemplate.save(studentUpdateEvent, "nisvarthaEventStore");
	
	        	
	    			
	  			  String studentNumber = newnisvarthaRecord.getNisvarthastudentid();
	  			  String studentName = newnisvarthaRecord.getNisvarthastudentrecord().getStudentname();
	  		      String studentEmail = newnisvarthaRecord.getNisvarthastudentrecord().getEmailid();
	  		      String studentOperation = "Student addition";
	  		        
	  	//		ExternalStudentRoutingService.studentNotificationServiceInvoke(studentName, studentNumber, studentEmail, studentOperation);
	  		    

	        	
	        	return studentUpdate;
	        
	        }
	   }
	                return studentUpdate;
	}
	***/
    public String saveUserCredentials(UserCredentials userCredentials ){
    	System.out.println("USER CREDENTIAL DTO DATA ::" +userCredentials.toString());
       Date date = Calendar.getInstance().getTime();
       DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
       String strDate = dateFormat.format(date);
       userCredentials.setUpdatedDate(strDate);
       //String randompassword = RandomStringUtils.random(8, "IA*b#$LOVE#@!*%NISVARTHA$#2?39008acegkmors089061450BDEHJN$#9");
       //System.out.println("Random password" +randompassword);
       //Base64.Encoder encoder = Base64.getEncoder();
       //String encodedString = encoder.encodeToString(randompassword.getBytes(StandardCharsets.UTF_8) );
       //System.out.println("ENCODED password" +encodedString);
       //.Decoder decoder = Base64.getDecoder();
       //byte[] decodedByteArray = decoder.decode(encodedString);
//       System.out.println(new String(decodedByteArray));
//       System.out.println("PASSWORD ABOUT TO BE SET TO DB :"+encodedString);
//   	   System.out.println("sha password set to db:"+DigestUtils.sha256Hex(encodedString));
//       userCredentials.setPassword(DigestUtils.sha256Hex(encodedString));
       
       userCredentials.setPassword("v");
       userCredentials.setActive("Active");
       String randomid = RandomStringUtils.random(5, "ABCDEFG12345862");
       userCredentials.setNisvarthaId("NFUSER"+randomid);
        mongoTemplate.save(userCredentials); 
       
//       UpdateResult writeResult = mongoTemplate.updateMulti(query, update, "students");
       
       // Assuming that the userCreadentials are always successfully created 
        

       Events userCreateEvent = new Events();
       

       	// this field to be introduced in Nisvartha record //// 
       //	studentEvent.setCreatedBy(adminUserCommand.getNisvarthastudentrecord().);
       //	studentEvent.setCreatedDate(dtf.format(now));
       
       userCreateEvent.setNisarthaID(userCredentials.getNisvarthaId());
       userCreateEvent.setCreatedBy(userCredentials.getUpdatedBy());
       userCreateEvent.setCreatedDate(strDate);
       userCreateEvent.setEventID(EventType.USERCREATED);
       userCreateEvent.setDomain(EventType.DOMAIN_ADMINISTRATION);
       userCreateEvent.setDomainEventType(EventType.USERCREDENTIALSCREATED);
       userCreateEvent.setPayload(userCredentials.toString());
       
   	mongoTemplate.save(userCreateEvent, "nisvarthaEventStore");

       return "Successfully Created User";
     }
    
    
    //use case of username and password changes
    public Boolean updateUserCredentials(UserCredentials userCredentials ){ 
    	Boolean userUpdate = false;
        
    	Date date = Calendar.getInstance().getTime();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	String strDate = dateFormat.format(date);

    	UserCredentials existingUserCred = new UserCredentials();
    	Query query = new Query();
    	query.addCriteria(Criteria.where("nisvarthaId").is(userCredentials.getNisvarthaId()));
    	existingUserCred = mongoTemplate.findOne(query, UserCredentials.class, "credentials");


        userCredentials.setUpdatedDate(strDate);
        
        System.out.println(" I am here ??????????");
        Update update = new Update();
        System.out.println(userCredentials.getUserName()+"---USERNFID---"+userCredentials.getNisvarthaId());
    //    update.set("nisvarthaId", userCredentials.getNisvarthaId());
        update.set("userName", userCredentials.getUserName());
        update.set("password", existingUserCred.getPassword());
        System.out.println(">>>>>>>> New updated active status" +userCredentials.getActive());
       // update.set("active",userCredentials.getActive());
        update.set("updatedBy", userCredentials.getUpdatedBy());
        update.set("updtedDate",strDate);
        update.set("roles",userCredentials.getRoles());
        System.out.println("Updated user: " +update.toString());
        
	    UpdateResult writeResult = mongoTemplate.updateMulti(query, update, UserCredentials.class, "credentials");

		
        if(writeResult.getModifiedCount()!=0) {
        	if(writeResult.getMatchedCount()!=0) {
				userUpdate = true;
				
				Events userCredentialsUpdateEvent = new Events();

			
			    userCredentialsUpdateEvent.setNisarthaID(userCredentials.getNisvarthaId());
				userCredentialsUpdateEvent.setCreatedBy(userCredentials.getUpdatedBy());
				userCredentialsUpdateEvent.setCreatedDate(strDate);
				userCredentialsUpdateEvent.setEventID(EventType.USERCREDENTIALSUPDATED);
				userCredentialsUpdateEvent.setDomain(EventType.DOMAIN_ADMINISTRATION);
				userCredentialsUpdateEvent.setPayload(userCredentials.toString());
			        
			    mongoTemplate.save(userCredentialsUpdateEvent, "nisvarthaEventStore");
			       
			       String userName = userCredentials.getUserName();
			       //String userEmail = userCredentials.getEmail();
			       String customerEmail = "champaka.tn@gmail.com";
			       String operation = "UserCreated";
			       
	//		       extersnalUserRoutingService.UserNotificationServiceInvoke(userName, customerEmail, operation);
			 return userUpdate;
			 
        	}
        }

        return userUpdate;
     }
	
    public void deleteuserbyname(String userName ) {
		
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        mongoTemplate.findAndRemove(query, UserCredentials.class, "credentials");
        

    	Date date = Calendar.getInstance().getTime();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	String strDate = dateFormat.format(date);
    	
		Events userCredentialsDeleteEvent = new Events();

		
		userCredentialsDeleteEvent.setCreatedBy(userName);
		userCredentialsDeleteEvent.setCreatedDate(strDate);
		userCredentialsDeleteEvent.setEventID(EventType.USERDELETED_ID);
		userCredentialsDeleteEvent.setDomain(EventType.DOMAIN_ADMINISTRATION);
		userCredentialsDeleteEvent.setDomainEventType(EventType.USERCREDENTIALSDELETED);
		
}
    /****
    public void RegisterCandidate(Candidate candidateData){ 
    	
    	System.out.println("Inside regCandidate \n"+candidateData);
    	StudentStatus studentStatus = new StudentStatus();
    	
    	String nfStatus = "PENDING";
    	String currentStatus = "PENDING";
    	String updatedBy = "admin";
    	
    	
        Date date = Calendar.getInstance().getTime();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	    String updatedDate = dateFormat.format(date);
    	
	   studentStatus.setNfStatus(nfStatus);
	   studentStatus.setCurrentStatus(currentStatus);
	   studentStatus.setUpdatedBy(updatedBy);
	   studentStatus.setUpdatedDate(updatedDate);
	   
	   String test = "NFTAN"+RandomStringUtils.random(5, "MYINDIAKARNATAKAGREAT$#1234567890");
	   candidateData.setApplicationFormNumber(test);
	   candidateData.setStudentStatus(studentStatus);
	    
    	
       mongoTemplate.save(candidateData);
       
    }
*****/	
}



