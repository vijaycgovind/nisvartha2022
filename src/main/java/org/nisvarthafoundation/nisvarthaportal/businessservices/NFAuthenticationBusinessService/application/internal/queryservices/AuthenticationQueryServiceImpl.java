package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.application.internal.queryservices;


import java.security.SecureRandom;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.rules.AuthenticationConstants;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.rules.userRolesConstants;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects.LoginUser;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects.UserRoles;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects.UserStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.infrastructure.repositories.JPA.userValidationRepository;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.interfaces.rest.dto.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Application Service which caters to all queries related to the Booking Bounded Context
 */
@Service
public class AuthenticationQueryServiceImpl implements AuthenticationQueryService  {
	
	private boolean status;//local status of authentication

    
    // creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate;
    
    @Autowired
    private userValidationRepository repository;
    
      
	@Override
	public UserStatus isAValidUser(LoginUserDTO validateLoginUserDetails) {
	   status=false;  
	  //fetch the password
	    LoginUser lu= repository.findByuserName(validateLoginUserDetails.getUserName());
	    //check for password match
	    UserStatus userStatus = new UserStatus();
	    userStatus.setNfSuperadmin(false);
	    userStatus.setNfAdmin(false);
	    userStatus.setNfFinance(false);
	    userStatus.setNfleadMentor(false);
	    userStatus.setNfMentor(false);
	    userStatus.setNfCorporate(false);
	    
	    if(lu!=null)
	    {
	    	System.out.println("value sent by rect page"+ validateLoginUserDetails.getPassword());
	    
	    	//String sha256hex = DigestUtils.sha256Hex(validateLoginUserDetails.getPassword());
	    	//System.out.println("password sha engrypted from react:"+sha256hex);
	    	//System.out.println("password fetched from database   :"+lu.getPassword());
	    	
	    	System.out.println(lu.getPassword() + "- password both -" +validateLoginUserDetails.getPassword() );
	    	
	      //if((lu.getPassword().equals(sha256hex)) && (lu.getActive().equals(AuthenticationConstants.USER_ACTIVE_STATUS)))
	    	if((lu.getPassword().equals(validateLoginUserDetails.getPassword())) && (lu.getActive().equals(AuthenticationConstants.USER_ACTIVE_STATUS)))	 
	         {   	    	
	  	         status=true;
	  	         //System.out.println("INSIDE VALIDATION AND SUCCESS");
	  	         userStatus.setStatus("true");
	  	         ArrayList<String> arr = lu.getRoles();
	  	         
	  	         for (int i=0; i< arr.size();i++) {
	  	        	 
	  	        	 
	  	        	 if(arr.get(i).equals(userRolesConstants.NFSUPERADMIN) ) {
	  	        		userStatus.setNfSuperadmin(true);	
	  	        		 break;
	  	        		
	  	        	 }
	  	        	 if(arr.get(i).equals(userRolesConstants.NFADMIN)) {
	  	        		 userStatus.setNfAdmin(true);
	  	        	 }

	  	        	 if(arr.get(i).equals(userRolesConstants.NFMENTOR)) {
	  	        		 userStatus.setNfMentor(true);
	  	        	
	  	        	 }
	  	        	 if(arr.get(i).equals(userRolesConstants.NFLEADMENTOR)) {
	  	        		 userStatus.setNfleadMentor(true);
	  	        	 }
	  	        	 if(arr.get(i).equals(userRolesConstants.NFFINANCE)) {
	  	        		 userStatus.setNfFinance(true);
	  	        		
	  	        	 }
	  	        	 if(arr.get(i).equals(userRolesConstants.NFCORPORATE)) {
	  	        		 System.out.println("in CORPORTe role");
	  	        		 userStatus.setNfCorporate(true);
	  	        	
	  	        	 }

	  	         }
	  	        

	         	}
	  	       }  
	    
	       return userStatus;
	       
	        
	        
	  }

	@Override
	public boolean isAValidTAFUser(LoginUserDTO validateLoginUserDetails) {
		   status=false;		   
		    String applicationformnumber = validateLoginUserDetails.getUserName();		
		    String reverseapplicationformnumber = new StringBuffer(applicationformnumber).reverse().toString();					 
		    if(validateLoginUserDetails.getPassword().equals(reverseapplicationformnumber))	  	    	  
			         {   
		    	
			  	         status=true;
			         }  			      
	
			        return status;
			    		        
			  }
	
	@Override
	public List<UserRoles> findAllRolesNF() {
    
		return mongoTemplate.findAll(UserRoles.class, "roles");
	
		
	}


			  

}
