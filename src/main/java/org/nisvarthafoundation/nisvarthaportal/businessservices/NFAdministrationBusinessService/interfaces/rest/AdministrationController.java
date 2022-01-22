package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest;


import java.util.List;




import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.application.internal.commandservices.AdministrationCommandService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.application.internal.queryservices.AdministrationQueryService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaFlashMessage;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaStudentAdmissionProcessRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest.dto.NisvarthaRecordDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest.dto.NisvarthaStudentAdmissionProcessDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest.tranform.AdministrationDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

   @CrossOrigin(origins = { "http://localhost:4200" })
	@Controller    // This means that this class is a Controller
	@RequestMapping("/Administration")
	public class AdministrationController {	

	@Autowired
    private AdministrationQueryService administrationQueryService;	
	
	@Autowired
    private AdministrationCommandService administrationCommandService;	
	
	
	
    // creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate; 

    @GetMapping("/listStudents")
    @ResponseBody
    public List<NisvarthaRecord> listAllApprovedStudents(){
    return administrationQueryService.getAllNisvarthaStudents();
    }
    
    @GetMapping("/getStudentDetails/{nisvarthastudentid}")
    @ResponseBody
    public NisvarthaRecord getNisvarthaStudentProvidedNisvarthaID(@PathVariable String nisvarthastudentid){
    return administrationQueryService.getNisvarthaStudentProvidedNisvarthaID(nisvarthastudentid);
    }
    
        
    @PostMapping("/createStudent")
    @ResponseBody
    public String saveStudentRecord(@RequestBody  NisvarthaRecordDTO nisvarthaRecorddto){   	
    	
    	 String x = administrationCommandService.saveStudentRecord(AdministrationDTOAssembler.toCommandFromDTO(nisvarthaRecorddto)); 
    	   	 
      return x;
  
        
    }
    
    @GetMapping("/getStudentAdmissionProcessDetails")
    @ResponseBody
    public NisvarthaStudentAdmissionProcessRecord getStudentAdmissionProcessDetails(){
    return administrationQueryService.getStudentAdmissionProcessDetails();
    }
    
    @PostMapping("/createStudentAdmissionProcessDetails")
    @ResponseBody
    public String createStudentAdmissionProcess(@RequestBody NisvarthaStudentAdmissionProcessRecord nisvarthaStudentAdmissionProcessRecord) {
    
     return administrationCommandService.saveStudentAdmissionProcess(nisvarthaStudentAdmissionProcessRecord);
    
    }
    
    @GetMapping("/getFlashMessage")
    @ResponseBody
    public NisvarthaFlashMessage getFlashMessage(){
    	System.out.println("Test Flash Message");
    return administrationQueryService.getActiveFlashMessage();
    }
    
    @PostMapping("/createFlashMessage")
    @ResponseBody
    public String createFlashMessage(@RequestBody NisvarthaFlashMessage nisvarthaFlashMessage) {
    
     return administrationCommandService.saveNisvarthaMessage(nisvarthaFlashMessage);
    
    }
    
    
    
}


