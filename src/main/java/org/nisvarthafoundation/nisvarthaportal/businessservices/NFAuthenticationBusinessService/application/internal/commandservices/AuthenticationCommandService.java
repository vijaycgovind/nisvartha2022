package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.application.internal.commandservices;


import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationCommandService {
	
	
	// creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate;
   
    
    public double sicalculation() {
    	float principle = 5000; 
    	int duration = 2;
    	
    	double rate = 0.1;
    	double simpleInterest = principle * duration * rate/100 ;
    	
    	System.out.println(">>>>Simple Interest = " +simpleInterest);
    	return simpleInterest;
    }


 

}