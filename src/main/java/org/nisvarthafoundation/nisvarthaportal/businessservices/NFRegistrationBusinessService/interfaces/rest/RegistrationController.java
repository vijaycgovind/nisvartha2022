package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.bson.types.Binary;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.NFRegistrationExceptions.WrongInputException;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.application.internal.commandservices.RegistrationCommandService;


import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.application.internal.queryservices.RegistrationQueryService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.aggregates.ApplicationForm;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.aggregates.ApplicationFormNumber;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.LoginUser;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.User;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.CandidateRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.DocumentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.Documents;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.dto.ApplicationFormDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.dto.RejectCandidateDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.dto.SubscriptionDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.dto.UpdateCandiateStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.tranform.EmailSubscriptionDTOAssembler;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.tranform.RegistrationDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins = { "http://localhost:4200" })
@Controller    // This means that this class is a Controller
@RequestMapping("/Registration")
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationController {

@Autowired
   private RegistrationCommandService registrationCommandService;
@Autowired
   private RegistrationQueryService registrationQueryService;


final private String PROOF_SUBMITED="Yes";
final private String PROOF_NOT_SUBMITED="No";


@PostMapping("/registerUser")
   @ResponseBody
//   public ApplicationFormNumber saveUserApplicationForm(
		   public ResponseEntity<ApplicationFormNumber> saveUserApplicationForm(
    @RequestPart(value="applicationform") String applicationform,
    @RequestPart(value="applicationFormNumber",required = false) String applicationFormNumber,
    @RequestPart(value="applicationStatus",required = false) String applicationStatus,
	@RequestPart(value="casteProofFile",required = false) final MultipartFile casteProofFileReceived,
    @RequestPart(value="bplcardProofFile",required = false) final MultipartFile bplcardProofFileReceived,
    @RequestPart(value="incomeProofFile",required = false) final MultipartFile incomeProofFileReceived,
    @RequestPart(value="passbookProofFile",required = false) final MultipartFile passbookProofFileReceived,
    @RequestPart(value="housephotoProofFile",required = false) final MultipartFile housephotoProofFileReceived,
    @RequestPart(value="studentwriteupProofFile",required = false) final MultipartFile studentwriteupProofFileReceived,
    @RequestPart(value="parentwriteupProofFile",required = false) final MultipartFile parentwriteupProofFileReceived,
    @RequestPart(value="markscardProofFile",required = false) final MultipartFile markscardProofFileReceived,
    @RequestPart(value="signedapplicationformProofFile",required = false) final MultipartFile signedapplicationformProofFileReceived
                        
    
    ){
	

   
    ObjectMapper mapper = new ObjectMapper();
    ApplicationFormDTO applicationFormDTO = new ApplicationFormDTO();
    DocumentStatus theDocumentStatus = new DocumentStatus();
    applicationFormDTO.setDocumentStatus(theDocumentStatus);
    
    Documents theDocuments= new Documents();
    applicationFormDTO.setDocumentsSubmitted(theDocuments);
    

       
    try {
    User theapplicationform =  mapper.readValue(applicationform, User.class);
    
    System.out.println("incontroller --->" +applicationform.toString());
    
    applicationFormDTO.setApplicationform(theapplicationform);
    if(StringUtils.hasText(applicationFormNumber))
    {
    applicationFormDTO.setApplicationFormNumber(applicationFormNumber);
    }
    
    if(StringUtils.hasText(applicationStatus))
    {
   
    applicationFormDTO.setApplicationStatus(applicationStatus);
    
    }
   System.out.println("application form numbere---->"+ applicationFormDTO.toString());
   
    if(casteProofFileReceived!=null)
    {

    Binary casteProofFileReceivedBinary = new Binary(casteProofFileReceived.getBytes());
    applicationFormDTO.getDocumentsSubmitted().setCasteProof(casteProofFileReceivedBinary);
    applicationFormDTO.getDocumentStatus().setCasteProofSubmitted(this.PROOF_SUBMITED);
   
    }
    else
    {
    applicationFormDTO.getDocumentStatus().setCasteProofSubmitted(this.PROOF_NOT_SUBMITED);
    }
    
    if(bplcardProofFileReceived!=null)
    {
    	System.out.println("BPL Document attached ::" +bplcardProofFileReceived.getBytes() );
    
    Binary bplcardProofFileReceivedBinary = new Binary(bplcardProofFileReceived.getBytes());
    applicationFormDTO.getDocumentsSubmitted().setBplcardProof(bplcardProofFileReceivedBinary);
    applicationFormDTO.getDocumentStatus().setBplcardProofSubmitted(this.PROOF_SUBMITED);
    }
    else
    {
    	System.out.println("BPL Document not attached ::");
    applicationFormDTO.getDocumentStatus().setBplcardProofSubmitted(this.PROOF_NOT_SUBMITED);
    }
    
    if(incomeProofFileReceived!=null)
    {
    
    Binary incomeProofFileReceivedBinary = new Binary(incomeProofFileReceived.getBytes());
    applicationFormDTO.getDocumentsSubmitted().setIncomeProof(incomeProofFileReceivedBinary);
    applicationFormDTO.getDocumentStatus().setIncomeProofSubmitted(this.PROOF_SUBMITED);
    }
    else
    {
  
    applicationFormDTO.getDocumentStatus().setIncomeProofSubmitted(this.PROOF_NOT_SUBMITED);
    }
    
   
 if(passbookProofFileReceived!=null)
 {
     Binary passbookProofFileReceivedBinary = new Binary(passbookProofFileReceived.getBytes());
     applicationFormDTO.getDocumentsSubmitted().setPassbookProof(passbookProofFileReceivedBinary);
     applicationFormDTO.getDocumentStatus().setPassbookProofSubmitted(this.PROOF_SUBMITED);
 }
 else
 {
	 applicationFormDTO.getDocumentStatus().setPassbookProofSubmitted(this.PROOF_NOT_SUBMITED);
 }
    
 if(housephotoProofFileReceived!=null)
 {
     Binary housephotoProofFileReceivedBinary = new Binary(housephotoProofFileReceived.getBytes());
     applicationFormDTO.getDocumentsSubmitted().setHousephotoProof(housephotoProofFileReceivedBinary);
     applicationFormDTO.getDocumentStatus().setHousephotoProofSubmitted(this.PROOF_SUBMITED);
 }
 else
 {
	 applicationFormDTO.getDocumentStatus().setHousephotoProofSubmitted(this.PROOF_NOT_SUBMITED);
 }
    
 if(studentwriteupProofFileReceived!=null)
 {
     Binary studentwriteupProofFileReceivedBinary = new Binary(studentwriteupProofFileReceived.getBytes());
     applicationFormDTO.getDocumentsSubmitted().setStudentwriteupProof(studentwriteupProofFileReceivedBinary);
     applicationFormDTO.getDocumentStatus().setStudentwriteupProofSubmitted(this.PROOF_SUBMITED);
 }
 else
 {
	 applicationFormDTO.getDocumentStatus().setStudentwriteupProofSubmitted(this.PROOF_NOT_SUBMITED);
 }
 
 if(parentwriteupProofFileReceived!=null)
 {
    
     Binary parentwriteupProofFileReceivedBinary = new Binary(parentwriteupProofFileReceived.getBytes());
     applicationFormDTO.getDocumentsSubmitted().setParentwriteupProof(parentwriteupProofFileReceivedBinary);
     applicationFormDTO.getDocumentStatus().setParentwriteupProofSubmitted(this.PROOF_SUBMITED);
 }
 else
 {
	 applicationFormDTO.getDocumentStatus().setParentwriteupProofSubmitted(this.PROOF_NOT_SUBMITED);
 }
    
    if(markscardProofFileReceived!=null)
    {
     Binary markscardProofFileReceivedBinary = new Binary(markscardProofFileReceived.getBytes());
     applicationFormDTO.getDocumentsSubmitted().setMarkscardProof(markscardProofFileReceivedBinary);
     applicationFormDTO.getDocumentStatus().setMarkscardProofSubmitted(this.PROOF_SUBMITED);
    }
    else
    {
    	applicationFormDTO.getDocumentStatus().setMarkscardProofSubmitted(this.PROOF_NOT_SUBMITED);
    }
    
    if(signedapplicationformProofFileReceived!=null)
    {
    	System.out.println("Inside signed applicaiton");
     Binary signedapplicationformProofFileReceivedBinary = new Binary(signedapplicationformProofFileReceived.getBytes());
     applicationFormDTO.getDocumentsSubmitted().setSignedapplicationformProof(signedapplicationformProofFileReceivedBinary);
     applicationFormDTO.getDocumentStatus().setSignedapplicationformProofSubmitted(this.PROOF_SUBMITED);
    }
    else
    {
    	System.out.println("signed application is not submitted and in else loop");
    	applicationFormDTO.getDocumentStatus().setSignedapplicationformProofSubmitted(this.PROOF_NOT_SUBMITED);
    }
   
} catch (IOException e1) {

e1.printStackTrace();
}
   
   
   System.out.println("dto value"+applicationFormDTO.toString());
//   ApplicationFormNumber applicationFormNumberCreated = registrationCommandService.registerUser(RegistrationDTOAssembler.toCommandFromDTO(applicationFormDTO));
        ApplicationFormNumber applicationFormNumberCreated = null;
         applicationFormNumberCreated  = registrationCommandService.registerUser(RegistrationDTOAssembler.toCommandFromDTO(applicationFormDTO));
if(applicationFormNumberCreated == null) {
	return  (ResponseEntity<ApplicationFormNumber>) (ResponseEntity.status(HttpStatus.BAD_REQUEST));

}
    
 //  return applicationFormNumberCreated;
return ResponseEntity.of(Optional.of(applicationFormNumberCreated));
       
   }
   
   
   @GetMapping("/allApplictionForm/count")
   @ResponseBody
   public long getCountofApplicationForms(){
   
    return registrationQueryService.getCountofApplicationForms();
 
   }
   
   @GetMapping("/pendingApplicationForms/count")
   @ResponseBody
   public ResponseEntity<Long> getPendingFormsCount(){
	   long pendingFormCount = 0;
	   pendingFormCount = registrationQueryService.getPendingFormsCount();
//		if(pendingFormCount == 0) {
//		return  (ResponseEntity<Long>) (ResponseEntity.status(HttpStatus.NOT_FOUND));
//		}
		return ResponseEntity.of(Optional.of(pendingFormCount));

 
   }
   
   @GetMapping("/partialApplicationForms/count")
   @ResponseBody
   public ResponseEntity<Long> getPartialFormsCount(){
   
	   long partialFormCount = 0;
	   partialFormCount = registrationQueryService.getPartialFormsCount();
		if(partialFormCount == 0) {
		return  (ResponseEntity<Long>) (ResponseEntity.status(HttpStatus.NOT_FOUND));
		}
		return ResponseEntity.of(Optional.of(partialFormCount));

   }
   
 
   
   @GetMapping("/pendingApplicaitonForms")
   @ResponseBody
   public ResponseEntity<List<ApplicationForm>> getPendingFormsList(){

	   System.out.println("Inside applicaiton pending forms CONTROLLER");
	   
	   List<ApplicationForm> listForm = null;
	   
	   listForm = registrationQueryService.getPendingFormsList();
		   if(listForm.isEmpty()) {
			   return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		   }
		   return ResponseEntity.of(Optional.of(listForm));
	   // return registrationQueryService.getPendingFormsList();
   }
   
   @GetMapping("/getTAFDetails/{applicationFormNumber}")
   @ResponseBody
   public ResponseEntity<ApplicationForm> getTempApplicationFormDetails(@PathVariable String applicationFormNumber){

	   if(applicationFormNumber==null || applicationFormNumber.isEmpty() || applicationFormNumber.isBlank())
	   {
		   throw new WrongInputException("Please provide valid applicaiton form Number"); 
		   
	   }
	   
	   ApplicationForm formDetails = null;
	   formDetails = registrationQueryService.getTempApplicationFormDetails( applicationFormNumber);
	   if(formDetails == null) {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	   }
	   return ResponseEntity.of(Optional.of(formDetails));
	//   return registrationQueryService.getTempApplicationFormDetails( applicationFormNumber);

   }
   
   @GetMapping("/getAllTAFDetails/{applicationFormNumber}")
   @ResponseBody
   public ResponseEntity<ApplicationForm> getAllDataOfTempApplicationFormDetails(@PathVariable String applicationFormNumber){
	   
	   if(applicationFormNumber==null || applicationFormNumber.isEmpty() || applicationFormNumber.isBlank())
	   {
		   throw new WrongInputException("Please provide valid applicaiton form Number"); 
		   
	   }
	   
	   ApplicationForm formDetails = null;
	   formDetails = registrationQueryService.getAllDataOfTempApplicationFormDetails( applicationFormNumber);
	   if(formDetails == null) {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	   }
	   return ResponseEntity.of(Optional.of(formDetails));

   }
   
   @PostMapping("/rejectCandidate")
   @ResponseBody
   public String rejectCandidate(@RequestBody  RejectCandidateDTO theRejectCandidateDTO)
   {
   	
   	System.out.println("In registration  Controller  - candidate rejected "  + theRejectCandidateDTO.getApplicationFormNumber());
   	
   	registrationCommandService.rejectApplicationForm(theRejectCandidateDTO);

   return "Status Updated";
   }
   

   
   @GetMapping("/getDocuments/{applicationFormNumber}/{documentType}")
   @ResponseBody
   public ResponseEntity<byte[]> getAttachedDocument(@PathVariable String applicationFormNumber,@PathVariable String documentType){
	

	   ApplicationForm tempApplicationForm = registrationQueryService.getAllDataOfTempApplicationFormDetails(applicationFormNumber);
	   //System.out.println("data in getDocuments ----> "+tempApplicationForm.toString());
	   
	   if("bplCardProof".equalsIgnoreCase(documentType))
	   {
	   
	   return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "bplcard"+"-"+ applicationFormNumber +".pdf"+ "\"")
		        .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
		        .body(tempApplicationForm.getDocumentsSubmitted().getBplcardProof().getData());
	   }



	   if("incomeProof".equalsIgnoreCase(documentType))
	   {
	   
	   return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "incomeProof"+"-"+ applicationFormNumber +".pdf"+ "\"")
		        .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
		        .body(tempApplicationForm.getDocumentsSubmitted().getIncomeProof().getData());
	   }
	   
	   if("passbookProof".equalsIgnoreCase(documentType))
	   {
	   
	   return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "passbook"+"-"+ applicationFormNumber +".pdf"+ "\"")
		        .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
		        .body(tempApplicationForm.getDocumentsSubmitted().getPassbookProof().getData());
	   }
	   
	   if("housephotoProof".equalsIgnoreCase(documentType))
	   {
	   
	   return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "housephotoProof"+"-"+ applicationFormNumber +".pdf"+ "\"")
		        .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
		        .body(tempApplicationForm.getDocumentsSubmitted().getHousephotoProof().getData());
	   }
	   if("studentwriteupProof".equalsIgnoreCase(documentType))
	   {
	   
	   return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "studentwriteupProof"+"-"+ applicationFormNumber +".pdf"+ "\"")
		        .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
		        .body(tempApplicationForm.getDocumentsSubmitted().getStudentwriteupProof().getData());
	   }
	   
	   if("parentwriteupProof".equalsIgnoreCase(documentType))
	   {
	   
	   return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "parentwriteupProof"+"-"+ applicationFormNumber +".pdf"+ "\"")
		        .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
		        .body(tempApplicationForm.getDocumentsSubmitted().getParentwriteupProof().getData());
	   }

	   if("markscardProof".equalsIgnoreCase(documentType))
	   {
	   
	   return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "markscardProof"+"-"+ applicationFormNumber +".pdf"+ "\"")
		        .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
		        .body(tempApplicationForm.getDocumentsSubmitted().getMarkscardProof().getData());
	   }
	   
	   if("casteProof".equalsIgnoreCase(documentType))
	   {
	   
	   return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "casteProof"+"-"+ applicationFormNumber +".pdf"+ "\"")
		        .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
		        .body(tempApplicationForm.getDocumentsSubmitted().getCasteProof().getData());
	   }
	   
	   if("signedapplicationformProof".equalsIgnoreCase(documentType))
	   {
	   
	   return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "signedapplicationformProof"+"-"+ applicationFormNumber +".pdf"+ "\"")
		        .header(HttpHeaders.CONTENT_TYPE,"application/pdf")
		        .body(tempApplicationForm.getDocumentsSubmitted().getSignedapplicationformProof().getData());
	   }
       return null;
   }
   
   
   @PostMapping("/Candiate/status")
   @ResponseBody
      public ResponseEntity<String> updateCandidateStatus(@RequestBody  UpdateCandiateStatus updateStatusDTO) {
	   
	   
       String Status = null;
       Status  = registrationCommandService.updateCandidateStatus(RegistrationDTOAssembler.toCommandFromDTO(updateStatusDTO));
       if(Status == null) {
	return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return ResponseEntity.of(Optional.of(Status));
	//   return registrationCommandService.updateCandidateStatus(RegistrationDTOAssembler.toCommandFromDTO(updateStatusDTO));
   	  
   }
   
   
   

@PostMapping("/Auth/isValidNFTAN")
@ResponseBody
   public boolean isAValidNFTAN(@RequestPart(value="formData") String formData) {
	
	
	ObjectMapper mapper = new ObjectMapper();
    LoginUser lu = new LoginUser();
  
    try {
    	
    	if(lu!=null) {
    		lu =  mapper.readValue(formData, LoginUser.class);
    	}
    	else
    	{
    		log.info("Something went wrong while parsing login form object");
    	}
		
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 String theUserName= lu.getUserName();
	 String thePassword = lu.getPassword();
			 
	if(theUserName==null || theUserName.isBlank() || theUserName.isEmpty()) {
		
		throw new WrongInputException("Please provide valid userName : " + theUserName); 
		
	}
	else if(thePassword==null || thePassword.isBlank() || thePassword.isEmpty()) {
		
		throw new WrongInputException("Please provide valid password for the user "+ theUserName); 
		
	}

	else return registrationQueryService.isAValidNFTAN(lu);
}


 
   
}


