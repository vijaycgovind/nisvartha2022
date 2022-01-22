package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.application.internal.queryservices;

import java.util.Arrays;


import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaFlashMessage;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaStudentAdmissionProcessRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest.dto.NisvarthaStudentAdmissionProcessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



/**
 * Application Service which caters to all queries related to the Booking Bounded Context
 */
@Service
public class AdministrationQueryServiceImpl implements AdministrationQueryService  {
	
    
    // creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate;
    
    @Autowired
    RestTemplate restTemplate;  


    public List <NisvarthaRecord> getAllNisvarthaStudents() {
  		// find all and map to nisvartha students + refer to document/table from which data is to be fetched
    	Query query = new Query();
  		List<NisvarthaRecord> listofStudents = mongoTemplate.find(query, NisvarthaRecord.class, "students");
  		 System.out.println(listofStudents.toString());
  		return listofStudents;
  	}
    
    public NisvarthaRecord getNisvarthaStudentProvidedNisvarthaID(String nisvarthastudentid)
    {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("nisvarthastudentid").is(nisvarthastudentid));
    	System.out.println(nisvarthastudentid + "-------------"+query.toString());
    	return mongoTemplate.findOne(query, NisvarthaRecord.class);
}

	@Override
	public NisvarthaStudentAdmissionProcessRecord getStudentAdmissionProcessDetails() {
	
    	Query query = new Query();
    	return mongoTemplate.findOne(query, NisvarthaStudentAdmissionProcessRecord.class);
	}
	
	
	
	
	public NisvarthaFlashMessage getActiveFlashMessage()
    {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("status").is("active"));
         System.out.println(query.toString());
    	return mongoTemplate.findOne(query, NisvarthaFlashMessage.class);
}
	
	
   
   }
