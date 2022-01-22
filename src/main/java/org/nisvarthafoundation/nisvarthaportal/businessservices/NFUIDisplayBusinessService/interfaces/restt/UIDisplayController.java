package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.interfaces.restt;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.application.internal.queryservices.UIDisplayQueryService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Corporate;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.District;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Donor;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Member;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Mentor;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Occupation;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Patron;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.State;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;


	//import io.swagger.annotations.Api;
    @CrossOrigin(origins = { "http://localhost:4200" })
	@Controller    // This means that this class is a Controller
	@RequestMapping("/UIDisplay")
	@Api(value = "UI Display Controller",  description = " Endpoints for nisvartha UI display", tags = "UI Display Commands")
	public class UIDisplayController {
    	
    	private static final Logger logger = LogManager.getLogger(UIDisplayController.class);

	
		@Autowired
	    private UIDisplayQueryService uiDisplayQueryService;

	    /**
	     * GET method to retrieve a operational states in which nisvartha operates to fund students
	     *  @return
	     */
	    @GetMapping("/findOperationalStates/count")
	    @ResponseBody
	    public long findCountofStatesInWhichNisvarthaOperatesUI(){
	    return uiDisplayQueryService.findByOperationalStates();
	    }
	    
	    /**
	     * GET count of mentors supporting NF
	     *  @return
	     */
	    @GetMapping("/findMentors/count")
	    @ResponseBody
	    public long findCountofMentors(){
	    return uiDisplayQueryService.findCountofMentorsSupportingNF();
	    }
	    
	    /**
	     * GET method to retrieve all mentors for dropdown list
	     *  @return
	     */
	   
	    
	    @GetMapping("/listmentors")
	    @ResponseBody
	    
	    public List<Mentor> findAllMentors(){
	    //	logger.info("About to call states list");
	    	List<Mentor> mentorList = uiDisplayQueryService.findAllMentorsforUIdisplay();
	    	

	    	return mentorList; 
	    	
	    }
	    
	    /**
	     * GET method to retrieve all mentors for dropdown list
	     *  @return
	     */
	   
	    
	    @GetMapping("/liststudents")
	    @ResponseBody
	    
	    public List<StudentData> findAllStudents(){
	    	logger.info("About to call states list");
	    	List<StudentData> studentList = uiDisplayQueryService.findAllStudentsforUIdisplay();
	    	System.out.println("Student Details : >>>>>>>>>>>" +studentList);
	    	
	    	return  studentList; 
	    	
	    }
	    //////////////////////////////////////////////
	    
	    @GetMapping("/mentorname")
	    
	    public List<String> mentorname(){
	    	logger.info("About to call states list");
	    	List<String> mentorNames = uiDisplayQueryService.findAllMentorsbyName();
	    	System.out.println("Student Details : >>>>>>>>>>>" +mentorNames);
	    	
	    	return  mentorNames; 
	    	
	    }
	     
	    /**
	     * GET method to retrieve all indian states for dropdown list
	     *  @return
	     */
	   // @GetMapping("/findAllStates1")
	    //@ResponseBody
	   // public List<State> findAllStatesForUIDisplay1(){
	   // return uiDisplayQueryService.findAllStates();
	   // }
	    
	    
	    @RequestMapping(value = "/findAllStates", method = RequestMethod.GET)
	    public ResponseEntity<List<State>> findAllStatesForUIDisplay() {			
			logger.info("About to call states list");
			List<State> stateList = uiDisplayQueryService.findAllStates();
	        if (stateList.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<State>>(stateList, HttpStatus.OK);

		}
	    
	    
	    
	    /**
	     * GET method to retrieve a corporates in which is funding NF
	     *  @return
	     */
	    @GetMapping("/findCorporates/count")
	    @ResponseBody
	    public long getCountOfCorporatesSupportingNF(){
	    return uiDisplayQueryService.findCountOfCorporateSupportingNF();
	    }
	    
	    

	    
	    
	    /**
	     * GET method to retrieve all corporates
	     *  @return
	     */
	  	    
	    @RequestMapping(value = "/findAllCorporates", method = RequestMethod.GET)
	    public ResponseEntity<List<Corporate>> listAllCorporates() {			
			logger.info("About to call corporate list");
			List<Corporate> corporateList = uiDisplayQueryService.findAllCorporateSupportingNF();
	        if (corporateList.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Corporate>>(corporateList, HttpStatus.OK);

		}
	    
	    
	    
	    
	    

	    /**
	     * GET method to retrieve a patron in which is funding NF
	     *  @return
	     */
	    @GetMapping("/findPatrons/count")
	    @ResponseBody
	    public long getCountOfPatronsSupportingNF(){
	    return uiDisplayQueryService.findCountOfPatronSupportingNF();
	    }
	    
	    /**
	     * GET method to retrieve all patrons
	     *  @return
	     */
	    @GetMapping("/findAllPatrons")
	    @ResponseBody
	    public List<Patron> getAllPatronsSupportingNF(){
	    return uiDisplayQueryService.findAllPatronsSupportingNF();
	    }
	    
	    
	    /**
	     * GET method to retrieve a patron in which is funding NF
	     *  @return
	     */
	    @GetMapping("/findDonors/count")
	    @ResponseBody
	    public long getCountOfDonorsSupportingNF(){
	    return uiDisplayQueryService.findCountOfDonorSupportingNF();
	    }
	    
	    /**
	     * GET method to retrieve all patrons
	     *  @return
	     */
	    @GetMapping("/findAllDonors")
	    @ResponseBody
	    public List<Donor> getAllDonorsSupportingNF(){
	    return uiDisplayQueryService.findAllDonorsSupportingNF();
	    }
	    
	    /**
	     * GET method to retrieve all districts
	     *  @return
	     */
	    @GetMapping("/findAllDistricts")
	    @ResponseBody
	    public List<District> getAllDistricts(){
	    return uiDisplayQueryService.findAllDistricts();
	    }
	    
	    /**
	     * GET method to retrieve all Assd members
	     *  @return
	     */
	    @GetMapping("/findAllMembers")
	    @ResponseBody
	    public List<Member> getAllMemberSupportingNF(){
	    return uiDisplayQueryService.findAllMembersSupportingNF();
	    }
	    
	    /**
	     * GET method to retrieve count of Assd Members
	     *  @return
	     */
	    @GetMapping("/findMembers/count")
	    @ResponseBody
	    public long getCountOfMembersSupportingNF(){
	    return uiDisplayQueryService.findCountOfMembersSupportingNF();
	    }
	    
	    /**
	     * GET method to retrieve all Assd members
	     *  @return
	     */
	    @GetMapping("/findAllOccupation")
	    @ResponseBody
	    public List<Occupation> getAllOccupation(){
	    return uiDisplayQueryService.findAllOccupation();
	    }
	    
	
	    	    
	    
	}

