package org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.application.internal.queryservices;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Application Service which caters to all queries related to the Booking Bounded Context
 */
@Service
public class ReportingQueryServiceImpl implements ReportingQueryService  {	

    
    // creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate;
    
    @Autowired
    RestTemplate restTemplate;
    
    @RequestMapping(value = "/findStudents/count")
    public long findTotalStudentsofNivartha() {
    	
    	String localURL = "http://localhost:9002/Registration/findStudents/count";
    	
    	      HttpHeaders headers = new HttpHeaders();
    	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	      HttpEntity <String> entity = new HttpEntity<String>(headers);
    	      
    	      return restTemplate.exchange(localURL, HttpMethod.GET, entity, long.class).getBody();
    	 
	}


    
    
   

}
