package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.application.internal.queryservices;

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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



/**
 * Application Service which caters to all queries related to the Booking Bounded Context
 */
@Service
public class UIDisplayQueryServiceImpl implements UIDisplayQueryService  {	

    
    // creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate; 
  
	
	 //method inherited from mother interface
    
	public long findByOperationalStates() {
								
			Query query = new Query();
			// check on the states document where operational is set to Y
			query.addCriteria(Criteria.where("operational").is('Y'));
			//System.out.println("about to hit mongo"+query.toString());
			//Return the count to UI - ourwork page
			return mongoTemplate.count(query,State.class);
		}


	public List<State> findAllStates() {
		// find all and map to State Entity class + refer to document/table from which data is to be fetched
		List<State> listofStates = mongoTemplate.findAll(State.class, "states");
		return listofStates;
	}


	public long findCountOfCorporateSupportingNF() {
		// TODO Auto-generated method stub
		Query query = new Query();
		return mongoTemplate.count(query,Corporate.class);
	}


	public List<Corporate> findAllCorporateSupportingNF() {
		// TODO Auto-generated method stub
		List<Corporate> listofCorporates = mongoTemplate.findAll(Corporate.class, "corporates");
		return listofCorporates;
	}


	public long findCountOfPatronSupportingNF() {
		Query query = new Query();
		return mongoTemplate.count(query,Patron.class);
	}


	public List<Patron> findAllPatronsSupportingNF() {
		List<Patron> listofPatrons = mongoTemplate.findAll(Patron.class, "patrons");
		return listofPatrons;
	}


	public long findCountOfDonorSupportingNF() {
		Query query = new Query();
		return mongoTemplate.count(query,Donor.class);
	}


	public List<Donor> findAllDonorsSupportingNF() {
		List<Donor> listofDonors = mongoTemplate.findAll(Donor.class, "donors");
		return listofDonors;
	}


	public List<District> findAllDistricts() {
		List<District> listofDistricts = mongoTemplate.findAll(District.class, "districts");
		return listofDistricts;
	}
	
	public List<Mentor> findAllMentorsforUIdisplay(){
		
		Mentor mentorData = new Mentor();
		List<Mentor> listofMentors = mongoTemplate.findAll(Mentor.class , "mentors");

//		List<Mentor> sortedMentorList = listofMentors.stream()
//				 .sorted(Comparator.comparing(p->(mentorData.getId()+mentorData.getFirstName()+mentorData.getMiddleName())
//					 .collect(Collectors.toList());

		
//				List<String> MentorName = listofMentors.stream()
//								.map(Mentor->String.format("%s%s%s",Mentor.getId(),Mentor.getFirstName(),Mentor.getMiddleName()))
//								.collect(Collectors.toList());
		
		 return listofMentors;
		// return MentorName;
	}
	

	public List<String> findAllMentorsbyName(){
		
		List<Mentor> MentorsList = mongoTemplate.findAll(Mentor.class , "mentors");
		List<String> MentorName = MentorsList.stream()
								.map(Mentor->String.format("%s%s",Mentor.getFirstName(),Mentor.getMiddleName()))
								.collect(Collectors.toList());
		
		return MentorName;
		
	}
	
	public List<StudentData> findAllStudentsforUIdisplay() {
		
		String city = "Male";
		StudentData studentData = new StudentData();
    	Query query = new Query();
    	
    	query.addCriteria(Criteria.where("personalDetails.sex").is(city));
    	
		System.out.println(">>>>>>>>>Inside Serv IMPL");

		List<StudentData> listofStudents = new ArrayList<>();
		
		listofStudents = mongoTemplate.find(query, StudentData.class, "students");
//		listofStudents.forEach(System.out::println);
		System.out.println("UUUUUUUUUUUUUUUUUUUU?????????????????????????");	
 // 	#################Comparator Functionality #############################
 		List<StudentData> sortedStudentList = listofStudents.stream()
 					 .sorted(Comparator.comparing(p->studentData.getPersonalDetails().getStudentName()))
 						 .collect(Collectors.toList());
 		
 		
 
		
//		List<StudentData> listofStudents1 = mongoTemplate.findAll(StudentData.class, "students" );
		// return listofStudents;
return sortedStudentList;
		
		//		############# Filter  functionalit 2 greator than words #########################
//		List<StudentData> sortedStudentList2 = listofStudents.stream()
//				.filter(p->p.getFamilyDetails().getFamilyAnnualIncome().length() > 4 )
//				.collect(Collectors.toList());
//		
//		sortedStudentList2.forEach(System.out::println);
//		//sortedStudentList2.forEach(System.out::println);
//		
//		if (listofStudents == null) {
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> INSIDE");
//			throw new  Exception("Users not found");
//			}
//		
//	//	return sortedStudentList2;	
//		return listofStudents;
		
	}

	/**********************************
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
//	@Override
//	public List<StudentData> getStudentDetails(String city) throws Exception {
//
//		try {
//		StudentData studentData = new StudentData();
//    	logger.info("inside SERVICE IMPLS Search studentsDetails by city");
//    	Query query = new Query();
//    	
//    	query.addCriteria(Criteria.where("personalDetails.contact.address.city").is(city));
//    	
//		System.out.println(">>>>>>>>>Inside Serv IMPL");
//
//		/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
//	   // 	List<StudentData> studentsDataList = new ArrayList<>();
//			
//			List<StudentData> listofStudents = new ArrayList<>();
//		
//		//	Map<String, StudentData> listofStudents = new HashMap<>();
//			
//		listofStudents = mongoTemplate.find(query, StudentData.class, "student");
//		//listofStudents.forEach(System.out::println);
//	
// // 	#################Comparator Functionality #############################
////		List<StudentData> sortedStudentList = listofStudents.stream()
//////					 .sorted(Comparator.comparing(studentData->studentData.getpersonalDetails().getstudentName()))
////						 .collect(Collectors.toList());
//		
//		System.out.println("Filter functionality");
////	 	################# Filter  Functionality1 #############################
////		List<StudentData> sortedStudentList1 = listofStudents.stream()
////	      .filter(p->p.getEducationDetails().getCurrentStatus().equalsIgnoreCase("Active"))
////	      .collect(Collectors.toList());
//		
//		
////		############# Filter  functionalit 2 greator than words #########################
//		List<StudentData> sortedStudentList2 = listofStudents.stream()
//				.filter(p->p.getFamilyDetails().getFamilyAnnualIncome().length() > 4 )
//				.collect(Collectors.toList());
//		
//		sortedStudentList2.forEach(System.out::println);
//		//sortedStudentList2.forEach(System.out::println);
//		
//		if (listofStudents == null) {
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> INSIDE");
//			throw new  Exception("Users not found");
//			}
//		
//	//	return sortedStudentList2;	
//		return listofStudents;
//	//	return sortedStudentList;
//
//	} catch  (Exception e) {
//			logger.error(e.getMessage());
//			return null;
//		}
////		System.out.println("returning null");
////		return null;
//		
//}

	
	
	/****************************
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	public long findCountOfMembersSupportingNF() {
		Query query = new Query();
		return mongoTemplate.count(query,Member.class);
	}


	public List<Member> findAllMembersSupportingNF() {
		List<Member> listofMember = mongoTemplate.findAll(Member.class, "associateMembers");
		return listofMember;
	}


	public List<Occupation> findAllOccupation() {
		List<Occupation> listofOccupation = mongoTemplate.findAll(Occupation.class, "occupation");
		return listofOccupation;
	}


	public long findCountofMentorsSupportingNF() {
		Query query = new Query();
		return mongoTemplate.count(query,Mentor.class);
	}
	
	/**
	 * 
	 * 
	 */
	

}
