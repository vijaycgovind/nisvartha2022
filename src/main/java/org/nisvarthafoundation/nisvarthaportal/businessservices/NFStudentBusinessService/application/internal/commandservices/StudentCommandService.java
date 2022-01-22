package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.commandservices;



import java.net.URI;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.LoginUser;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.Candidate;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.User;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.operations.commands.StudentCreateCommand;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.ApplicationForm;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.DocumentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Documents;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaFinanceRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaMentorRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Sponsorshiprecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.interfaces.rest.dto.UpdateCandiateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class StudentCommandService{

	@Autowired
	MongoTemplate mongoTemplate;
	RestTemplate restTemplate = new RestTemplate();
	

	public void createStudentRecord(StudentCreateCommand createStudentCommand) {
		
		String nisvarthaTempID =createStudentCommand.getNisvarthaTempTAFID();
		System.out.println("NEW STUDENT REGISTRAITON STARTS--" + nisvarthaTempID);
		String endPointForCandidateDetails = "http://localhost:9090/Registration/getAllTAFDetails/"+nisvarthaTempID;
		URI uri = null;
		try {
			uri = new URI(endPointForCandidateDetails);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ApplicationForm theApplicationForm = restTemplate.getForObject(uri, ApplicationForm.class); 
		System.out.println("value fetched from registations >>>>"+theApplicationForm.toString());
		NisvarthaRecord theNisvarthaRecord = new NisvarthaRecord();
		//temp setting all future records. the following 3 objects are for future updates but for now creating
		//empty objects for consistency
	    NisvarthaMentorRecord nisvarthamentorrecord = new NisvarthaMentorRecord();
		NisvarthaFinanceRecord nisvarthafinancerecord = new NisvarthaFinanceRecord();
		Sponsorshiprecord sponsorshiprecord= new Sponsorshiprecord();
		
			
		Date date = Calendar.getInstance().getTime();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	    String updatedDate = dateFormat.format(date);
	    // create nisvartha student Permanant Account Number NF- PAN
		String nfStudentPermanentID = "NFPAN-"+ Year.now()+"-"+RandomStringUtils.random(6, "WWA11233455SUCC6ESS77899ABBCCDDEEFFGGKKMMMLLVVEEKKAARRNNAATTAAKKAANNSSVVAARRTTHANDA223334444555556666666777777778888888889999999990"+System.currentTimeMillis()).toUpperCase();
		
	    theNisvarthaRecord.setNisvarthastudentid(nfStudentPermanentID);
	    theNisvarthaRecord.setNisvarthatempstudentid(theApplicationForm.getApplicationFormNumber());
	    theNisvarthaRecord.setApplicationStatus("APPROVED");
	    theNisvarthaRecord.setApplicationYear(Year.now().toString());
	    theNisvarthaRecord.setStudentActivestatus("Active");
	    theNisvarthaRecord.setUpdatedBy(createStudentCommand.getUpdatedBy());
	    theNisvarthaRecord.setUpdatedDate(updatedDate);
	    theNisvarthaRecord.setComments(createStudentCommand.getComments());
	    theNisvarthaRecord.setApplicationform(theApplicationForm.getApplicationform());
	    theNisvarthaRecord.setDocumentsSubmitted(theApplicationForm.getDocumentsSubmitted());
	    theNisvarthaRecord.setDocumentStatus(theApplicationForm.getDocumentStatus());
	    theNisvarthaRecord.setNisvarthamentorrecord(nisvarthamentorrecord);
	    theNisvarthaRecord.setNisvarthafinancerecord(nisvarthafinancerecord);
	    theNisvarthaRecord.setSponsorshiprecord(sponsorshiprecord);
        //CREATE NISVARTHA STUDENT RECORD
		mongoTemplate.save(theNisvarthaRecord);
		System.out.println("Nisvartha Student Creation COMPLETED-----");
        System.out.println("Nisvartha Candiate Status update to Approved Process STARTS ----");
        String endPointForCandidateStatus_UpdateToApproved = "http://localhost:9090/Registration/Candiate/status";

		try {
			uri = new URI(endPointForCandidateStatus_UpdateToApproved);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UpdateCandiateStatus theUpdateCandiateStatusDTO = new UpdateCandiateStatus();
		
		theUpdateCandiateStatusDTO.setCandiateTANumber(nisvarthaTempID);
		ResponseEntity<String> status= restTemplate.postForEntity(uri, theUpdateCandiateStatusDTO,String.class);
        System.out.println("Nisvartha Candiate Status update to Approved Process COMPLETED ----"+status.getStatusCode().value());
        int temp = status.getStatusCode().value();
		if(temp==200)
		{
			 System.out.println("Nisvartha Student Credential Creation STARTS ----");
			 String tempPassword = RandomStringUtils.random(6, "WWVVXXYYKKMPRSTULOVENDA23334444555556666666LOVENSVARTHA2777777778888888889999999990").toUpperCase();
             System.out.println("candidate PLAIN TEXT password -->"+tempPassword);
       	     LoginUser theStudentCredentials= new LoginUser();
       	     theStudentCredentials.setUserName(nfStudentPermanentID);
             String encodedPassword_base_64 = Base64.getEncoder().encodeToString(tempPassword.getBytes(StandardCharsets.UTF_8));
             System.out.println("BASE 64 Encoded Password-->"+encodedPassword_base_64);
             String hashedPassword = DigestUtils.sha256Hex(encodedPassword_base_64);
             theStudentCredentials.setPassword(hashedPassword);
             Date date1 = Calendar.getInstance().getTime();
             DateFormat dateFormat1 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
             String strDate1 = dateFormat1.format(date1);
             theStudentCredentials.setUpdatedDate(strDate1);
             theStudentCredentials.setActive("Active");
             String[] candidateRole = new String[] {"Student"};
             theStudentCredentials.setRoles(candidateRole);
             theStudentCredentials= mongoTemplate.save(theStudentCredentials);
             if(theStudentCredentials!=null)
             {
            	 log.info("PLEASE PLUG IN NOFICIAITON COMPONET HERE");
             }
             else
             {
            	 log.info("SEND EMAIL ASKING FOR USER NAME AND PASSWORD FROM NISVARTHA");
             }
           
			
		}
		  else
          {
        	  log.info("Student login credential creation failed"+ System.currentTimeMillis());
 
        	 
          }
		log.info("Nisvartha Student Credential Creation COMPLETED ----");
		
		
	}
  

	

	
public void studentMentorAssign(String studentName, String nisvarthastudentid,String mentorName,  String updatedBy ) {
		

	        Query query = new Query();
	        query.addCriteria(Criteria.where("nisvarthastudentid").is(nisvarthastudentid));
	        System.out.println("QUERY criteria ******** " +query.toString());
	        Update update = new Update();
	        
            update.set("nisvarthamentorrecord.mentorname", mentorName);
            
            update.set("updatedBy", updatedBy);
            mongoTemplate.upsert(query, update, NisvarthaRecord.class);
}
	
	public ResponseEntity<HttpStatus> deleteStudentbyID(String studentId ) {
		
		System.out.print("Inside StudentDelete in Student Command Service");
		   try {
	        Query query = new Query();
	        query.addCriteria(Criteria.where("nisvarthastudentid").is(studentId));
	        System.err.println("QUERY criteria ******** " +query);
	        
	        System.out.println("Deletion in Mongo DB ");
	  
            mongoTemplate.findAndRemove(query, Candidate.class);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
	        }
		   catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	}		   
			
}
	

