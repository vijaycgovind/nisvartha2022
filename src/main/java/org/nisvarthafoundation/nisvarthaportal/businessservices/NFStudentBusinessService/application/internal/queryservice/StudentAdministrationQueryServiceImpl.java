package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.queryservice;

import java.util.Arrays;


import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.User;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Roles;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.UserCredentials;
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
public class StudentAdministrationQueryServiceImpl implements AdministrationQueryService  {
	
    
    // creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate;
    
    @Autowired
    RestTemplate restTemplate;  


    public List <NisvarthaRecord> getAllNisvarthaStudents() {
  		// find all and map to nisvartha students + refer to document/table from which data is to be fetched
    	Query query = new Query();
  		List<NisvarthaRecord> listofStudents = mongoTemplate.find(query, NisvarthaRecord.class, "students");
        System.out.println("list of approved studetns" + listofStudents.toString());
  		return listofStudents;
  	}
    
    public NisvarthaRecord getNisvarthaStudentProvidedNisvarthaID(String nisvarthastudentid)
    {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("nisvarthastudentid").is(nisvarthastudentid));

    	return mongoTemplate.findOne(query, NisvarthaRecord.class);
}
    
    public List<UserCredentials> getUserCredentials(){
   
    	// mongoTemplate.find(query, entityClass)
    	return mongoTemplate.findAll(UserCredentials.class, "credentials");
    }
    
    public List<Roles> getUserroles(){
  
    	
    	// mongoTemplate.find(query, entityClass)
    	return mongoTemplate.findAll(Roles.class, "roles");
    	
    }
    
    public List<User> getPendingRegistrations(){

    	
    	Query query = new Query();
    	
    	query.addCriteria(Criteria.where("applicationStatus.currentStatus").is("PENDING"));
    	
    	List<User> myUser =  mongoTemplate.find(query, User.class);
    	
    	return myUser;
    	
    	
    }
    
   public Long getPendingRegistrationsCount(){
    	
  
    	Query query = new Query();
    	
    	query.addCriteria(Criteria.where("applicationStatus.currentStatus").is("PENDING"));
    	
    	Long count =  mongoTemplate.count(query, User.class);
    	
    	System.out.println(",,,, "+count);
    	return count;
    	
    	
    }
   
   }
