package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.interfaces.rest;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.types.Binary;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.commandservices.AdminMentorCommandService;
//import org.nisvarthafoundation.businessservices.NFStudentBusinessService.application.internal.commandservices.AdminStudentCommandService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.commandservices.StudentAdministrationCommandService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.commandservices.StudentCommandService;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.outboundservices.ExternalMentorRoutingService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.outboundservices.ExternalStudentRoutingService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.queryservice.AdministrationQueryService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.queryservice.StudentQueryService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.queryservice.adminMentorQuery;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.User;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Mentor;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.MentorAssign;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaDocumentStatusRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaStudentDocumentsRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaStudentRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Roles;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.SDocumentProperties;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentSummaryReport;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.UserCredentials;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.createStudent;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.interfaces.rest.tranform.createStudentAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MultipartBody.Part;
import springfox.documentation.schema.Model;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.Candidate;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/student")
public class StudentController {
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentCommandService studentCommandService;
	
	@Autowired
	StudentQueryService studentQueryService;
	
	@Autowired
	AdminMentorCommandService adminMentorCommandService;
	
	@Autowired 
	adminMentorQuery adminmentorQueryService;
	
	@Autowired
	ExternalStudentRoutingService externalStudentRoutingService;
	

	@Autowired
	ExternalMentorRoutingService externalMentorRoutingService;
	
	@Autowired 
	StudentAdministrationCommandService administrationCommandService;
	
	@Autowired
	StudentAdministrationCommandService administrationUserCommand;
	
	@Autowired
	AdministrationQueryService administrationQueryService;
	
//	@Autowired
//	AdminStudentCommandService theAdminStudentCommandService;
	
	@Autowired
	StudentCommandService theStudentCommandService;
	


	final private String PROOF_SUBMITED="Yes";
	final private String PROOF_NOT_SUBMITED="No";
	
	

//	@PutMapping("/update")
//	
//	public String updatingStudent(@RequestBody AdministrationUserCommand useradminCommand) {
//		
//		Boolean result = administrationCommandService.StudentUpdatebyID(useradminCommand);
//		
//
//		return "student updated successfully";
//		
//	}
	
	
	@PostMapping("/approvecandidate")
	@ResponseBody
	   public String createStudentRecord(@RequestBody createStudent createstudeentdata)
	  
	   {
		theStudentCommandService.createStudentRecord(createStudentAssembler.toCommandFromDTO(createstudeentdata));
		return "Student created - success";
	   }
	

	@PostMapping("/mentoradd")
	@ResponseBody
	public void saveMentorData(@RequestBody Mentor mentordata) {
	
		adminMentorCommandService.mentorSetup(mentordata);
		
		logger.info("Inside  Controller : mentorCreate"); 
		
		
        String mentorname = mentordata.getMentorname();
        String mentoremail = "champaka.tn@gmail.com";
        String mentoroperation = "mentor addition";
       
        externalMentorRoutingService.MentorNotificationServiceInvoke(mentorname, mentoremail, mentoroperation);
	}
	
	@PutMapping("/mentorupdate")
	@ResponseBody
	public void updateMentorData(@RequestBody Mentor mentordata) {
		
		System.out.println("In St Controller - Mentor Update"+ mentordata.toString());
	
		adminMentorCommandService.mentorUpdate(mentordata, mentordata.getNisvarthamentorId());
	
	}
	
	@GetMapping("/mentorcount")
	public ResponseEntity<Long>  MentorCount() {
		long mCount = 0;
		mCount = adminmentorQueryService.getCountofMentors();
		if(mCount == 0) {
		return (ResponseEntity<Long>) (ResponseEntity.status(HttpStatus.NOT_FOUND));
		}
		return ResponseEntity.of(Optional.of(mCount));
		//return mCount;
		
	}
	
	@GetMapping("/mentorcountbycity/{city}")
	public ResponseEntity<Long> MentorCount(@PathVariable String city) {
	
		long mentorCountByCity = 0;
		mentorCountByCity  = adminmentorQueryService.getCountofMentorsByCity(city);
	       if(mentorCountByCity == 0) {
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	       }
	       return ResponseEntity.of(Optional.of(mentorCountByCity));
		//return ( adminmentorQueryService.getCountofMentorsByCity(city));
		
		
	}
	
	@GetMapping("/mentordetailsbystudent/{stName}")
	public ResponseEntity<List<Mentor>> Mentordetails(@PathVariable String stName) {
		
		String studentName = stName;
		List<Mentor> mentorList = null;
		mentorList = (List<Mentor>) adminmentorQueryService.getMentorDetailsByStudent(studentName);
		 
	    if(mentorList.isEmpty()) {
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	       }
	       return ResponseEntity.of(Optional.of(mentorList));
	//	return mentorList;
		
	}
	
	@GetMapping("/mentorlist")
	public ResponseEntity<List<Mentor>> Mentorlist() {
		List<Mentor> mentorList = null;
		mentorList = (List<Mentor>) adminmentorQueryService.getMentorList();
		if(mentorList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(mentorList));
		
		//return mentorList;
		
	}
	
	@GetMapping("/mentorEmailAddress")
	public ResponseEntity<List<Mentor>> listOfMentorEmails() {
		
		List<Mentor> mentorEmails = null;
		mentorEmails = (List<Mentor>) adminmentorQueryService.getMentorEmailAddress();
		if(mentorEmails.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(mentorEmails));
		
		
	//	return mentorEmails;
		
	}
	

	
	@PutMapping ("/assignmentor")
	@ResponseBody
	
	public void MentorStudentAssignment(@RequestBody MentorAssign mentorAssign){
	System.out.println(mentorAssign.toString());
		
		studentCommandService.studentMentorAssign(mentorAssign.getStudentname(), mentorAssign.getNisvarthastudentid(), 
												  mentorAssign.getMentorname(), mentorAssign.getUpdatedBy());
	
	}

	
	//================================Mentors Ends=======================================//
	
	//================================UserCredentials Starts ============================//
	
	@PostMapping("/createusercredentials")
	@ResponseBody
	
	    public void createUser(@RequestBody UserCredentials userCredentials) {
		System.out.println("In Controller - createUser" +userCredentials.toString());
		
		
		
		administrationCommandService.saveUserCredentials(userCredentials);
	}
	
	@GetMapping("/getallusers")
	
	public ResponseEntity<List<UserCredentials>> fetchUsers() {
		
		System.out.println("inControlle - get Users");
		
//		return administrationQueryService.getUserCredentials();
		List<UserCredentials> userList = null;
		userList = administrationQueryService.getUserCredentials();
		if(userList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(userList));
		
	}
	
	@PutMapping("/updateusercredentials")
	@ResponseBody
	public String updateUserdetails(@RequestBody UserCredentials userCredentials) {
		
		
		System.out.println(">>>in Contr >>>" +userCredentials.toString());
			
		administrationCommandService.updateUserCredentials(userCredentials);
		
		return "User credentials updated successfully";
	}
	
@GetMapping("/getallroles")
	
	public List<Roles> fetchroles() {
		
		System.out.println("inControlle");
		
		return administrationQueryService.getUserroles();
		
	}

@DeleteMapping("/deleteUsers/{userName}")

 public void dropusers(@PathVariable String userName) {
	System.out.println("In Ctr- dlelte Users");
	
	administrationCommandService.deleteuserbyname(userName);
	
}
//======================================================User Credentials Ends ===========================//
	

	
	/*****
	 * Update Student data with assigned mentor
	 * @return
	 */
		
	@GetMapping("/hello")
	public String HelloWorld (){
		//added comment
		
		logger.info("Hello World Method accessed from logger");
		// System.out.print("Hello World function");a
		return "Hello World in Student Business service";
		
	}
	
	@GetMapping("/nostudents")
	public ResponseEntity<Long> getCountofNisvarthaStudents() {
	
		long count = 0;
		
		count = studentQueryService.getCountofStudents();
		if(count == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(count));
	//	return studentQueryService.getCountofStudents();
	}
	
	@GetMapping("city/{city}")
	public ResponseEntity<Long> SearchStudentsByCity(@PathVariable  String city) {
		
		logger.info("inside ctrl Search students by city");
			long count = 0;
			
			count = studentQueryService.getStudentsCountByCity(city);
			if(count == 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(count));
		
	}
	
	
	@GetMapping("school/{course}")
	public ResponseEntity<Long> SearchStudentCountByCurrentCourse(@RequestBody String status , @PathVariable String course) {
		
		logger.info("inside ctrl Search students by course");
		System.out.println("Status >>>>>> " +status);
		
		String currentStatus = status;
		logger.info("inside ctrl Search students by city");
		long count = 0;
		
		count = studentQueryService.getCountOfStudentsByCurrentCourse(course, currentStatus);
		if(count == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(count));
		
	}
	

	
	@GetMapping("status/{status}")
	public ResponseEntity<Long> SearchStudentssByStatus(@PathVariable  String status) {
		
		logger.info("inside ctrl Search students by city");
		 long count = 0;
		 
		count = studentQueryService.getStudentscountByStatus(status);
		if(count == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(count));

	}
	
		 
	

	
	@DeleteMapping("/deletestudent/{nisvarthastudentid}")
	public ResponseEntity<HttpStatus> SearchstudentSummaryreport(@PathVariable  String nisvarthastudentid) {
		
		logger.info("Inside Controller - delete Student");
		try {
		studentCommandService.deleteStudentbyID(nisvarthastudentid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		 catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	
	}
	
	@DeleteMapping("/deletementor/{nisvarthamentorId}")
	public ResponseEntity<HttpStatus> dropMentorbyID(@PathVariable String nisvarthamentorId ) {
		logger.info("Inside Controller - delete Student");
		System.out.println("In cont ......"+nisvarthamentorId);
		try {
			adminMentorCommandService.deleteMentorbyID(nisvarthamentorId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			 catch (Exception e) {
				    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	///////////////// Imported from ADMIN COMMAND ///////////////////////
	

    @GetMapping("/getStudentDetails/{nisvarthastudentid}")
    @ResponseBody
    public ResponseEntity<NisvarthaRecord> getNisvarthaStudentProvidedNisvarthaID(@PathVariable String nisvarthastudentid){
    	
    	 NisvarthaRecord theNisvarthaRecord = null;
		 
    	 theNisvarthaRecord = administrationQueryService.getNisvarthaStudentProvidedNisvarthaID(nisvarthastudentid);
 		if(theNisvarthaRecord == null) {
 			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 		}
 		return ResponseEntity.of(Optional.of(theNisvarthaRecord));
 //   return administrationQueryService.getNisvarthaStudentProvidedNisvarthaID(nisvarthastudentid);
    }
    
    @GetMapping("/listStudents")
    @ResponseBody
    public ResponseEntity<List<NisvarthaRecord>> listAllApprovedStudents(){
    	List<NisvarthaRecord> theNisvarthaRecord = null;
		 
   	 theNisvarthaRecord = administrationQueryService.getAllNisvarthaStudents();
		if(theNisvarthaRecord == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(theNisvarthaRecord));
		
    	// return administrationQueryService.getAllNisvarthaStudents();
    }
   
////////Registration start===========///////
    
    @GetMapping("/getpendingregistrations")
    @ResponseBody
    
    public List<User> fetchAllPendingRegistrations(){
    	
    	System.out.println(">>>>>>>> In Contr .... PR");
    	
    	return administrationQueryService.getPendingRegistrations();
    	
    }

    @GetMapping("/getpendingcount")
    public Long fetchpendingcount() {
    	
    	return (administrationQueryService.getPendingRegistrationsCount());
    }
    
    @PostMapping("/register")
    @ResponseBody
    public void CandidateRegistration(@RequestBody User user)
    {
    	
    	System.out.println("In Student Controller  - candidate registration");
    	
    	Candidate candidateData = new Candidate();
    	candidateData.setStudentName(user.getPersonalDetails().getStudentName());
    	candidateData.setEducationDetails(user.getEducationDetails());
    	candidateData.setFamilyDetails(user.getFamilyDetails());
    	candidateData.setIdentificationDetails(user.getIdentificationDetails());
    	candidateData.setPersonalDetails(user.getPersonalDetails());
    	
    	
  //  administrationCommandService.RegisterCandidate(candidateData);
    
    }
    
////////Registration End===========///////
    
    @GetMapping("/NisvarthaStudent/count")
    @ResponseBody
    public ResponseEntity<Long> getNisvarthaStudentsCount(){
    	
    	long count = 0;
		 
      	 count = studentQueryService.getNisvarthaStudentsCount();
   		if(count == 0) {
   			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
   		}
   		return ResponseEntity.of(Optional.of(count));
    	
   //  return studentQueryService.getNisvarthaStudentsCount();
  
    }
    
    @GetMapping("/studentSummary")
    @ResponseBody
    public ResponseEntity<StudentSummaryReport> getStudentSummary(){

    	StudentSummaryReport theStudentSummaryReport = null;
		 
    	theStudentSummaryReport = studentQueryService.getStudentSummary();
  		if(theStudentSummaryReport == null) {
  			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  		}
  		return ResponseEntity.of(Optional.of(theStudentSummaryReport));

    	//     return studentQueryService.getStudentSummary();
  
    }
    
    

    

    
}
