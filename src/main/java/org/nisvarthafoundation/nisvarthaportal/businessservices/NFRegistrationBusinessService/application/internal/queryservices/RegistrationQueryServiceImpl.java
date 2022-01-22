package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.application.internal.queryservices;


import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.NFRegistrationExceptions.ResourceNotFoundException;


import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.aggregates.ApplicationForm;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.LoginUser;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.NisvarthaRecord;

import java.io.ByteArrayOutputStream;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.Or;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;


/**
 * Application Service which caters to all queries related to the Booking Bounded Context
 */
@Service
public class RegistrationQueryServiceImpl implements RegistrationQueryService  {
	
	boolean status=false;

    
    // creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate;
    
    @Autowired
    RestTemplate restTemplate;  


    public long getCountofApplicationForms(){
    Query query = new Query();
    return mongoTemplate.count(query, ApplicationForm.class);
    }
    

    public long getNisvarthaStudentsCount(){    	
    Query query = new Query();
    return mongoTemplate.count(query, NisvarthaRecord.class);
    }

  	
	@Override
	public ApplicationForm getTempApplicationFormDetails(String applicationNumber) {	
		
		Query query = new Query();
		query.addCriteria(Criteria.where("applicationFormNumber").is(applicationNumber));
		query.fields().exclude("documentsSubmitted.casteProof");
		query.fields().exclude("documentsSubmitted.incomeProof");
		query.fields().exclude("documentsSubmitted.passbookProof");
		query.fields().exclude("documentsSubmitted.housephotoProof");
		query.fields().exclude("documentsSubmitted.studentwriteupProof");
		query.fields().exclude("documentsSubmitted.parentwriteupProof");
		query.fields().exclude("documentsSubmitted.bplcardProof");
		query.fields().exclude("documentsSubmitted.markscardProof");
		query.fields().exclude("documentsSubmitted.signedapplicationformProof");
		ApplicationForm theApplicationForm = mongoTemplate.findOne(query, ApplicationForm.class);
		if(theApplicationForm!=null)
	   	{
			
			return theApplicationForm;
	   	}
	   	throw new ResourceNotFoundException("No application form found for the given id : " + applicationNumber);
	 
	   }
	
	@Override
	public ApplicationForm getAllDataOfTempApplicationFormDetails(String applicationNumber) {	
		
		Query query = new Query();
		query.addCriteria(Criteria.where("applicationFormNumber").is(applicationNumber));
		ApplicationForm theApplicationForm = mongoTemplate.findOne(query, ApplicationForm.class);
		
		if(theApplicationForm!=null)
	   	{
			
			return theApplicationForm;
	   	}
	   	throw new ResourceNotFoundException("No application form found for the given id : " + applicationNumber);
	 
	   }
	
	@Override
	public List<ApplicationForm> getPendingFormsList() {	
		
		System.out.println("About to get list of pending applications");
		
		Query query = new Query();
		int currentYear = Year.now().getValue();
		
		// create query and fetch application forms that have all the attachments but for signed applicaiton form
		//signed application form will be later uploaded by candidate after the approval of his request
		// this method will list all pending forms with all required data so that admin of nisvartha need not have to 
		// vet the applicaiton form for missing details
		query.addCriteria(
			    new Criteria().andOperator(
			    		Criteria.where("applicationYear").is(String.valueOf(currentYear)),
			    		Criteria.where("applicationStatus").is("PENDING"),
			    		Criteria.where("documentStatus.casteProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.incomeProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.passbookProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.housephotoProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.studentwriteupProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.parentwriteupProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.bplcardProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.markscardProofSubmitted").is("Yes")
			    )
			);
		query.fields().include("applicationFormNumber");
		query.fields().include("applicationStatus");
		query.fields().include("applicationYear");
	
       List <ApplicationForm> filteredApplicationForm =  mongoTemplate.find(query, ApplicationForm.class);

       return filteredApplicationForm;
	   }

	
	@Override
	public long  getPendingFormsCount() {	
		
		Query query = new Query();
		int currentYear = Year.now().getValue();
		
		query.addCriteria(
			    new Criteria().andOperator(
			    		Criteria.where("applicationYear").is(String.valueOf(currentYear)),
			    		Criteria.where("applicationStatus").is("PENDING"),
			    		Criteria.where("documentStatus.casteProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.incomeProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.passbookProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.housephotoProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.studentwriteupProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.parentwriteupProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.bplcardProofSubmitted").is("Yes"),
			    		Criteria.where("documentStatus.markscardProofSubmitted").is("Yes")
			    )
			);
		long k= mongoTemplate.count(query, ApplicationForm.class);
		System.out.println(k);
       return k;
	   }
	
	@Override
	public long  getPartialFormsCount() {	
		
		Query query = new Query();
		int currentYear = Year.now().getValue();
		
		query.addCriteria(
			    new Criteria().andOperator(
			    		Criteria.where("applicationYear").is(String.valueOf(currentYear)),
			    		Criteria.where("applicationStatus").is("PENDING")
			    		.orOperator(
			    				Criteria.where("documentStatus.casteProofSubmitted").is("No"),
					    		Criteria.where("documentStatus.incomeProofSubmitted").is("No"),
					    		Criteria.where("documentStatus.passbookProofSubmitted").is("No"),
					    		Criteria.where("documentStatus.housephotoProofSubmitted").is("No"),
					    		Criteria.where("documentStatus.studentwriteupProofSubmitted").is("No"),
					    		Criteria.where("documentStatus.parentwriteupProofSubmitted").is("No"),
					    		Criteria.where("documentStatus.bplcardProofSubmitted").is("No"),
					    		Criteria.where("documentStatus.markscardProofSubmitted").is("No")
			    				)
			    )
			);
       return mongoTemplate.count(query, ApplicationForm.class);
	   }

	
	@Override
	public boolean isAValidNFTAN(LoginUser validateLoginUserDetails) {

		    status=false;
		    Query query = new Query();
		    query.addCriteria(Criteria.where("userName").is(validateLoginUserDetails.getUserName()));
		    String sha256hex = DigestUtils.sha256Hex(validateLoginUserDetails.getPassword());
		    LoginUser lu = mongoTemplate.findOne(query, LoginUser.class);
		    System.out.println("User name:" + validateLoginUserDetails.getUserName());
	    	System.out.println("password sha engrypted from react:"+sha256hex);
	    	
	    	System.out.println("password fetched from database   :"+lu.getPassword());
	    	 
		    if(lu!=null)
		    {
		    	
		    		if(sha256hex.equals(lu.getPassword()))	  	    	  
			         {   
		    	
			  	         status=true;
			  	        
			         }

		    }
		    else
		    {
		    	System.out.println("Something went wrong during login for -->"+validateLoginUserDetails.getUserName());
		    	status=false;
		    }
		    
		 return status;
	           
			    		        
			  }

   }
