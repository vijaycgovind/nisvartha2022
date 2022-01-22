package org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.interfaces.rest;


import org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.application.internal.commandservices.ContactUSCommandService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.application.internal.queryservices.ReportingQueryService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.interfaces.rest.dto.ContactUSDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.interfaces.rest.tranform.RegisterContactUSDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



	//import io.swagger.annotations.Api;
@CrossOrigin(origins = { "http://localhost:4200" })
	@Controller    // This means that this class is a Controller
	@RequestMapping("/Reporting")
	//@Api(value = "Reporting Controller",  description = " Endpoints for nisvartha Reporting", tags = "Reporting Commands")
	public class ReportingController {

	
		@Autowired
	    private ReportingQueryService reportingQueryService;
		@Autowired
	    private ContactUSCommandService contactUSCommandService;
		
		


	    /**
	     * Make a REST call to UIDisplayBoundedContext to fetch list of all state in which nisvrtha operates
	     *  @return
	     */
	    @GetMapping("/findOperationalStates/count")
	    @ResponseBody
	    public long findStatesInWhichNisvarthaOperates(){
	    	
	    	    final String uritoUIDomain_FetchCountofStates = "http://localhost:9090/UIDisplay/findOperationalStates/count";
	    	    RestTemplate restTemplate = new RestTemplate();
	    	    return restTemplate.getForObject(uritoUIDomain_FetchCountofStates, long.class);	    	  
	    }
	    
	    /**
	     * Make a REST call to UIDisplayBoundedContext to fetch list of all state in which nisvrtha operates
	     *  @return
	     */
	    @GetMapping("/findCorporates/count")
	    @ResponseBody
	    public long getAllCorporateSupportingNF(){
	    	
	    	    final String uritoUIBoundedContext_FetchCountofCorporates = "http://localhost:9090/UIDisplay/findCorporates/count";
	    	    RestTemplate restTemplate = new RestTemplate();
	    	    return restTemplate.getForObject(uritoUIBoundedContext_FetchCountofCorporates, long.class);	    	  
	    }
	    
	    /**
	     * Make a REST call to UIDisplayBoundedContext to fetch student count in which nisvrtha operates
	     *  @return
	     */	    
	    @GetMapping("/findStudents/count")
	    @ResponseBody
	    public long findTotalStudentsofNisvartha(){
	    return reportingQueryService.findTotalStudentsofNivartha();
	    }
	    
	    /**
	     * Make a REST call to UIDisplayBoundedContext to fetch count of partons
	     *  @return
	     */
	    @GetMapping("/findPatrons/count")
	    @ResponseBody
	    public long getAllPatronSupportingNF(){
	    	
	    	    final String uritoUIBoundedContext_FetchCountofPatrons = "http://localhost:9090/UIDisplay/findPatrons/count";
	    	    RestTemplate restTemplate = new RestTemplate();
	    	    return restTemplate.getForObject(uritoUIBoundedContext_FetchCountofPatrons, long.class);	    	  
	    }
	    
	    /**
	     * Make a REST call to UIDisplayBoundedContext to fetch count of donors
	     *  @return
	     */
	    @GetMapping("/findDonors/count")
	    @ResponseBody
	    public long getAllDonorSupportingNF(){
	    	
	    	    final String uritoUIBoundedContext_FetchCountofDonors = "http://localhost:9090/UIDisplay/findDonors/count";
	    	    RestTemplate restTemplate = new RestTemplate();
	    	    return restTemplate.getForObject(uritoUIBoundedContext_FetchCountofDonors, long.class);	    	  
	    }
	    
	    /**
	     * Make a REST call to UIDisplayBoundedContext to fetch count of assd members
	     *  @return
	     */
	    @GetMapping("/findMembers/count")
	    @ResponseBody
	    public long getAllMemberSupportingNF(){
	    	
	    	    final String uritoUIBoundedContext_FetchCount = "http://localhost:9090/UIDisplay/findMembers/count";
	    	    RestTemplate restTemplate = new RestTemplate();
	    	    return restTemplate.getForObject(uritoUIBoundedContext_FetchCount, long.class);	    	  
	    }
	    
	    /**
	     * POST method to book a contact detail
	     * 
	     */

	    @PostMapping("/registerContactUSDetails")
	    @ResponseBody
	    public void saveContactUSDetails(@RequestBody  ContactUSDTO contactUSDTO){
	     
	         	contactUSCommandService.registerContactUSRequest(RegisterContactUSDTOAssembler.toCommandFromDTO(contactUSDTO));
	        
	    }
	    
	
	    
	}



