package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.application.internal.commandservices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaFlashMessage;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaStudentAdmissionProcessRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.operations.commands.AdministrationUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;



@Service
public class AdministrationCommandService{
	
	
  @Autowired
	MongoTemplate mongoTemplate;  	

    public String saveStudentRecord(AdministrationUserCommand administrationUserCommand){ 
    	
    	NisvarthaRecord nisvarthastudentrecord = new NisvarthaRecord(administrationUserCommand.getApplicationformnumber(),
    			administrationUserCommand.getNisvarthastudentid(),
    			administrationUserCommand.getNisvarthastudentrecord(),
    			administrationUserCommand.getNisvarthamentorrecord(),
    			administrationUserCommand.getNisvarthafinancerecord(),
    			administrationUserCommand.getSponsorshiprecord()
    			);    	

    	
       mongoTemplate.save(nisvarthastudentrecord); 
       return "Successfully Created Student";
     }
    
 public String saveNisvarthaMessage(NisvarthaFlashMessage nisvarthaFlashMessage){ 
	 
	    Date date = Calendar.getInstance().getTime();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	    String updatedDate= dateFormat.format(date);
	    nisvarthaFlashMessage.setUpdatedDate(updatedDate);
	    nisvarthaFlashMessage.setStatus("active");
    	
	 NisvarthaFlashMessage nisvarthastudentrecord = new NisvarthaFlashMessage(
			 nisvarthaFlashMessage.getFlashMessage(),
			 nisvarthaFlashMessage.getUpdatedBy(),
			 nisvarthaFlashMessage.getUpdatedDate(),
			 nisvarthaFlashMessage.getStatus()
    			);    	

	 Query query = new Query();
	 query.addCriteria(Criteria.where("status").is("active"));
	 Update update = new Update();
     update.set("status", "in-active");
	 mongoTemplate.updateMulti(query, update, NisvarthaFlashMessage.class);
    	
       mongoTemplate.save(nisvarthastudentrecord); 
       return "Successfully Created Student";
     }
    
    
    
    
public String saveStudentAdmissionProcess(NisvarthaStudentAdmissionProcessRecord nisvarthaStudentAdmissionProcessRecord){ 

	Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String updatedDate = dateFormat.format(date);
    
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

	Query query = new Query();
	query.addCriteria(Criteria.where("status").is("ACTIVE"));
    NisvarthaStudentAdmissionProcessRecord theNisvarthaStudentAdmissionProcessRecord= mongoTemplate.findOne(query, NisvarthaStudentAdmissionProcessRecord.class);
   System.out.println(theNisvarthaStudentAdmissionProcessRecord.toString());
    if(theNisvarthaStudentAdmissionProcessRecord!=null)
    {
   
    theNisvarthaStudentAdmissionProcessRecord.setCurrentYear(currentYear);
    theNisvarthaStudentAdmissionProcessRecord.setUpdatedDate(updatedDate);
    theNisvarthaStudentAdmissionProcessRecord.setLastDayForSubmission(nisvarthaStudentAdmissionProcessRecord.getLastDayForSubmission());
    theNisvarthaStudentAdmissionProcessRecord.setPucPercentageCutoff(nisvarthaStudentAdmissionProcessRecord.getPucPercentageCutoff());
    theNisvarthaStudentAdmissionProcessRecord.setTenthPecerntageCutoff(nisvarthaStudentAdmissionProcessRecord.getTenthPecerntageCutoff());
    theNisvarthaStudentAdmissionProcessRecord.setStatus("ACTIVE");
    theNisvarthaStudentAdmissionProcessRecord.setUpdatedBy(nisvarthaStudentAdmissionProcessRecord.getUpdatedBy());
    mongoTemplate.save(theNisvarthaStudentAdmissionProcessRecord); 
       return "Successfully updated Student Admission process details";
    }else
    {
    	return "Failed to update Student Admission process details";
    }
  
    }
     }

  



