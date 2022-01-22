package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.application.internal.commandservices;


import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.application.internal.queryservices.RegistrationQueryService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.aggregates.ApplicationForm;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.aggregates.ApplicationFormNumber;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.LoginUser;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.operations.commands.RegistrationUserCommand;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.operations.commands.UpdateCandidateStatusCommand;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.rules.Constants;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.CandidateRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.DocumentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.Documents;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.dto.RejectCandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.extern.slf4j.Slf4j;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.rules.Constants;

@Slf4j
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationCommandService {	
	// creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate; 
    
    @Autowired
    private RegistrationQueryService registrationQueryService;
    
    public ApplicationFormNumber registerUser(RegistrationUserCommand registrationUserCommand){
    	
    	final  String PROOF_SUBMITED="Yes";
   
    	
    	String theApplicationFormNumber = registrationUserCommand.getApplicationFormNumber();
    	System.out.println("Application form number already present--> "+theApplicationFormNumber);
    	
    	if(theApplicationFormNumber!=null)
    	{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("_id").is(theApplicationFormNumber));
    		
    		ApplicationForm theapplicationform = mongoTemplate.findOne(query, ApplicationForm.class);
    		System.out.println("Exising data fetcheed for candaite before updates-->" + theapplicationform.toString());
    		if(theapplicationform!=null) {
    				
    		theapplicationform.setApplicationform(registrationUserCommand.getApplicationform());
    		theapplicationform.setApplicationStatus(Constants.status);
    		theapplicationform.setApplicationYear(Year.now().toString());
    		Documents theUpdatedDocuments = registrationUserCommand.getDocuments();
    		DocumentStatus theUpdatedDocumentStatus = registrationUserCommand.getDocumentStatus();
    		
    		if("Yes".equalsIgnoreCase(theUpdatedDocumentStatus.getBplcardProofSubmitted()))
    		{
    	
    			theapplicationform.getDocumentsSubmitted().setBplcardProof(theUpdatedDocuments.getBplcardProof());
    			theapplicationform.getDocumentStatus().setBplcardProofSubmitted(PROOF_SUBMITED);
    			
    		}
    		
    		if("Yes".equalsIgnoreCase(theUpdatedDocumentStatus.getIncomeProofSubmitted()))
    		{
    			theapplicationform.getDocumentsSubmitted().setIncomeProof(theUpdatedDocuments.getIncomeProof());
    			theapplicationform.getDocumentStatus().setIncomeProofSubmitted(PROOF_SUBMITED);
    		}
    		
    		if("Yes".equalsIgnoreCase(theUpdatedDocumentStatus.getPassbookProofSubmitted()))
    		{
    			theapplicationform.getDocumentsSubmitted().setPassbookProof(theUpdatedDocuments.getPassbookProof());
    			theapplicationform.getDocumentStatus().setPassbookProofSubmitted(PROOF_SUBMITED);
    		}
    		if("Yes".equalsIgnoreCase(theUpdatedDocumentStatus.getHousephotoProofSubmitted()))
    		{
    			theapplicationform.getDocumentsSubmitted().setHousephotoProof(theUpdatedDocuments.getHousephotoProof());
    			theapplicationform.getDocumentStatus().setHousephotoProofSubmitted(PROOF_SUBMITED);
 
    		}
    		if("Yes".equalsIgnoreCase(theUpdatedDocumentStatus.getStudentwriteupProofSubmitted()))
    		{
    			theapplicationform.getDocumentsSubmitted().setStudentwriteupProof(theUpdatedDocuments.getStudentwriteupProof());
    			theapplicationform.getDocumentStatus().setStudentwriteupProofSubmitted(PROOF_SUBMITED);
 
    		}
    		if("Yes".equalsIgnoreCase(theUpdatedDocumentStatus.getParentwriteupProofSubmitted()))
    		{
    			theapplicationform.getDocumentsSubmitted().setParentwriteupProof(theUpdatedDocuments.getParentwriteupProof());
    			theapplicationform.getDocumentStatus().setParentwriteupProofSubmitted(PROOF_SUBMITED);
 
    		}
    		if("Yes".equalsIgnoreCase(theUpdatedDocumentStatus.getMarkscardProofSubmitted()))
    		{
    			theapplicationform.getDocumentsSubmitted().setMarkscardProof(theUpdatedDocuments.getMarkscardProof());
    			theapplicationform.getDocumentStatus().setMarkscardProofSubmitted(PROOF_SUBMITED);
 
    		}

    		if("Yes".equalsIgnoreCase(theUpdatedDocumentStatus.getCasteProofSubmitted()))
    		{
    			theapplicationform.getDocumentsSubmitted().setCasteProof(theUpdatedDocuments.getCasteProof());
    			theapplicationform.getDocumentStatus().setCasteProofSubmitted(PROOF_SUBMITED);
 
    		}
    		if("Yes".equalsIgnoreCase(theUpdatedDocumentStatus.getSignedapplicationformProofSubmitted()))
    		{
    			theapplicationform.getDocumentsSubmitted().setSignedapplicationformProof(theUpdatedDocuments.getSignedapplicationformProof());
    			theapplicationform.getDocumentStatus().setSignedapplicationformProofSubmitted(PROOF_SUBMITED);
 
    		}

       	     System.out.println("EXISTING USER AND UPDATEING THE DATA" + theapplicationform.toString());
     
       	     theapplicationform= mongoTemplate.save(theapplicationform);
    	
    		}
    		
    	}
    	else
    	{
    		
    		System.out.println("NEW CANDIDATE REGISTRAITON STARTS--");
    		theApplicationFormNumber = "NFTAN-"+ Year.now()+"-"+RandomStringUtils.random(6, "WWA11233455677899ABBCCDDEEFFGGKKMMMLLVVEEKKAARRNNAATTAAKKAANNSSVVAARRTTHANDA223334444555556666666777777778888888889999999990"+System.currentTimeMillis()).toUpperCase();
    		System.out.println("NEW NISVARTHA ID CREATED  ->"+theApplicationFormNumber);
            registrationUserCommand.setApplicationFormNumber(theApplicationFormNumber);
            registrationUserCommand.setApplicationStatus(Constants.status);
            System.out.println("doj"+registrationUserCommand.getApplicationform().getPersonalDetails().getDateofBirth());
            String studentdoj = registrationUserCommand.getApplicationform().getPersonalDetails().getDateofBirth();
            if(studentdoj!=null || studentdoj.isEmpty() || studentdoj.isBlank())
            {
            	 String [] dateParts = studentdoj.split("-");
                 String year = dateParts[0];
                 String month = dateParts[1];
                 String day = dateParts[2];    
           
                 LocalDate today = LocalDate.now();                   //Today's date         
                 LocalDate birthday = LocalDate.of(Integer.parseInt(year) , Integer.parseInt(month), Integer.parseInt(day)); //Birth date         
                 Period period = Period.between(birthday, today);
                 registrationUserCommand.getApplicationform().getPersonalDetails().setAge(period.getYears()); 
            
            }
            else
            {
            	log.info("Failed during creating of candidate Application form, please check the Date of Birth : " +studentdoj);
            
            }
              
            ApplicationForm theapplicationform = new ApplicationForm(
            										registrationUserCommand.getApplicationFormNumber(),
            										registrationUserCommand.getApplicationform(),
            										registrationUserCommand.getApplicationStatus(),
            										Year.now().toString(),
            										registrationUserCommand.getDocuments(),
            										registrationUserCommand.getDocumentStatus());
            
            theapplicationform= mongoTemplate.save(theapplicationform);
          if(theapplicationform!=null)
          {
              
        	  String tempPassword = RandomStringUtils.random(6, "WWVVXXYYKKMPRSTULOVENDA23334444555556666666LOVENSVARTHA2777777778888888889999999990").toUpperCase();
              System.out.println("candidate PLAIN TEXT password -->"+tempPassword);
        	  LoginUser theCandidateCredentials= new LoginUser();
              theCandidateCredentials.setUserName(theApplicationFormNumber);
              String encodedPassword_base_64 = Base64.getEncoder().encodeToString(tempPassword.getBytes(StandardCharsets.UTF_8));
              System.out.println("BASE 64 Encoded Password-->"+encodedPassword_base_64);
              String hashedPassword = DigestUtils.sha256Hex(encodedPassword_base_64);
              theCandidateCredentials.setPassword(hashedPassword);
              Date date = Calendar.getInstance().getTime();
              DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
              String strDate = dateFormat.format(date);
              theCandidateCredentials.setUpdatedDate(strDate);
              theCandidateCredentials.setActive("Active");
              String[] candidateRole = new String[] {"Candidate"};
              theCandidateCredentials.setRoles(candidateRole);
              theCandidateCredentials= mongoTemplate.save(theCandidateCredentials);
              if(theCandidateCredentials!=null)
              {
            	  System.out.println("PLEASE PLUG IN NOFICIAITON COMPONET HERE");
              }
              else
              {
            	  System.out.println("SEND EMAIL ASKING FOR USER NAME AND PASSWORD FROM NISVARTHA");
              }
              
        	  
          }
          else
          {
        	  log.info("Application form failed, please verify"+ System.currentTimeMillis());
        	  System.out.println("YOU NEED TO SEND APPLICAITON FORM FAILERU EMAIL");
        	  return new ApplicationFormNumber("000000");
        	 
          }
          
        }
    	return new ApplicationFormNumber(theApplicationFormNumber);
    }
	public CandidateRecord setCandidateRecord(ApplicationForm af) {
		
		CandidateRecord candidateRecord = new CandidateRecord();
		candidateRecord.setApplicationformnumber(af.getApplicationFormNumber());
		
	candidateRecord.setNisvarthastudentrecord(af.getApplicationform());
	candidateRecord.setDocumentStatus(af.getDocumentStatus());
	//candidateRecord.setNisvarthaStudentDocumentRecord(af.getDocumentsSubmitted());
		//candidateRecord.setNisvarthastudentrecord(af.getApplicationform());
		
			return candidateRecord;
		
	}
	public String updateCandidateStatus(UpdateCandidateStatusCommand commandFromDTO) {
		//updated registration status to Approved/Rejected
		String status="FAILURE";
		ApplicationForm theApplicationForm = registrationQueryService.getAllDataOfTempApplicationFormDetails(commandFromDTO.getCandiateTANumber());
		if(theApplicationForm!=null)
		{
			theApplicationForm.setApplicationStatus("APPROVED");
			mongoTemplate.save(theApplicationForm);
			status= "SUCCESS";
		}
		return status;
		
	}
    
	public String rejectApplicationForm(RejectCandidateDTO theRejectCandidateDTO) {
		//updated registration status to Approved/Rejected
		String status="SUCCESS";
		ApplicationForm theApplicationForm = registrationQueryService.getAllDataOfTempApplicationFormDetails(theRejectCandidateDTO.getApplicationFormNumber());
		if(theApplicationForm!=null)
		{
			theApplicationForm.setApplicationStatus("REJECTED");
			theApplicationForm.setUpdatedBy(theRejectCandidateDTO.getUpdatedBy());
			theApplicationForm.setComments(theRejectCandidateDTO.getComments());
			Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
			theApplicationForm.setUpdatedDate(strDate);
			mongoTemplate.save(theApplicationForm);
			status= "SUCCESS";
		}
		return status;
		
	}

    
    
}