package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.queryservice;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.count;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.facet;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.FacetOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentSummaryReport;



@Service
public class StudentQueryServiceImpl implements StudentQueryService{
	
	boolean status=false;
	
	Logger logger = LoggerFactory.getLogger(StudentQueryServiceImpl.class);

    
    // creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate;
    
    public long getCountofStudents()  {
    Query query = new Query();
    long test = mongoTemplate.count(query, NisvarthaRecord.class);
    
    return test ; 
    }

    
    public long getCountOfStudentsByCurrentCourse(String currentCourse, String currentStatus) {
    	
    	logger.info("inside SERVICE IMPLS Search students by Current Course");
    	long studentCityCount = 0;
    	Query query = new Query();
    	System.out.printf("Current STATUS >>>>" +currentStatus , " ++++++ Current COURSE >>>>>>" +currentCourse);
    	
    	String currentStatus1 = "Active";
    	 query.addCriteria(new Criteria("educationDetails.currentCourse").is(currentCourse).and("educationDetails.currentStatus").is(currentStatus1));
    	
    	System.out.println("Query " +query.toString());
    	studentCityCount = mongoTemplate.count(query, NisvarthaRecord.class);
    	
    	logger.info("exiting SERVICE IMPLS Search students by Course");
    	
    	return studentCityCount;
    }
    
    public long getStudentsCountByCity(String city) {
    	
    	logger.info("inside SERVICE IMPLS Search studentsDETAILS by AF NUMBER");
    	
    	long studentCityCount = 0;
    	Query query = new Query();
    	query.addCriteria(Criteria.where("personalDetails.contact.address.city").is(city));
    	
    	System.out.println("Query " +query.toString());
    	studentCityCount = mongoTemplate.count(query, NisvarthaRecord.class);
    	//studentCityCount = 17;	
    	logger.info("exiting SERVICE IMPLS Search students by city");
    	
    	return studentCityCount;
	
    }


//	@Override
//	public List<NisvarthaRecord> getStudentDetails(String city) throws Exception {
//
//		try {
//			NisvarthaRecord studentData = new NisvarthaRecord();
//    	logger.info("inside SERVICE IMPLS Search studentsDetails by city");
//    	Query query = new Query();
//    	
//    	query.addCriteria(Criteria.where("personalDetails.contact.address.city").is(city));
//    	
//		System.out.println(">>>>>>>>>Inside Serv IMPL");
//
//			
//			List<NisvarthaRecord> listofStudents = new ArrayList<>();
//			
//		listofStudents.forEach(System.out::println);
//		System.out.println(">>>>>>> LIST OF STUDENTS" +listofStudents);
//		
//		System.out.println("Filter functionality");
////	 	################# Filter  Functionality1 #############################
////		List<StudentData> sortedStudentList1 = listofStudents.stream()
////	      .filter(p->p.getEducationDetails().getCurrentStatus().equalsIgnoreCase("Active"))
////	      .collect(Collectors.toList());
//		
//		
////		############# Filter  functionalit 2 greator than words #########################
////		List<NisvarthaRecord> sortedStudentList2 = listofStudents.stream()
////				.filter(p->p.getFamilyDetails().getFamilyAnnualIncome().length() > 4 )
////				.collect(Collectors.toList());
//		
////		sortedStudentList2.forEach(System.out::println);
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
//			logger.info(e.getMessage());
//			logger.error(e.getMessage());
//			return null;
//		}
////		System.out.println("returning null");
////		return null;
//		
//}
		
 




	public NisvarthaRecord getStudentsdetailsByID(String studentNumber) {
    	
		NisvarthaRecord studentDetailsByNumber =  new NisvarthaRecord();
    	
        String studentNo = studentNumber;
        Query query = new Query();
      //  query.addCriteria(Criteria.where("_id").is(studentNo));
        //query.addCriteria(Criteria.where("student.id")).is(studentNo);
        query.addCriteria(Criteria.where("student.id").is(studentNumber));
        System.err.println("QUERY criteria ******** " +query);
        System.out.printf("  >>>>>>> ST NO<<<<<<<<< %s", studentNo);
        
        studentDetailsByNumber = mongoTemplate.findOne(query, NisvarthaRecord.class, "students");
        
       System.out.println("writeResult " +studentDetailsByNumber);
       return studentDetailsByNumber; 
    
    	
    }
	
	//fetch count of students
	 public long getNisvarthaStudentsCount(){    	
		    Query query = new Query();
		    return mongoTemplate.count(query, NisvarthaRecord.class);
		    }
	 



	public long getStudentscountByStatus(String status) {
		// TODO Auto-generated method stub
		return 0;
	}


	 
	 public StudentSummaryReport getStudentSummary()
	 {
		 


	 FacetOperation facetop = facet(
	 match(Criteria.where("nisvarthastudentrecord.educationrecord.previous.course").is("Medical").and("nisvarthastudentrecord.educationrecord.previous.status").is("completed")),
	 count().as("total")
	 ).as("CompletedTotalMedicalStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.current.course").is("Medical").and("nisvarthastudentrecord.educationrecord.current.status").is("active")),
	 count().as("total")
	 ).as("ActiveTotalMedicalStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.previous.course").is("Engineering").and("nisvarthastudentrecord.educationrecord.previous.status").is("completed")),
	 count().as("total")
	 ).as("CompletedTotalEngineeringStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.current.course").is("Engineering").and("nisvarthastudentrecord.educationrecord.current.status").is("active")),
	 count().as("total")
	 ).as("ActiveTotalEngineeringStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.previous.course").is("PUC").and("nisvarthastudentrecord.educationrecord.previous.status").is("completed")),
	 count().as("total")
	 ).as("CompletedTotalPUCStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.current.course").is("PUC").and("nisvarthastudentrecord.educationrecord.current.status").is("active")),
	 count().as("total")
	 ).as("ActiveTotalPUCStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.previous.course").is("Degree").and("nisvarthastudentrecord.educationrecord.previous.status").is("completed")),
	 count().as("total")
	 ).as("CompletedTotalDegreeStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.current.course").is("Degree").and("nisvarthastudentrecord.educationrecord.current.status").is("active")),
	 count().as("total")
	 ).as("ActiveTotalDegreeStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.previous.course").is("Diploma").and("nisvarthastudentrecord.educationrecord.previous.status").is("completed")),
	 count().as("total")
	 ).as("CompletedTotalDiplomaStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.current.course").is("Diploma").and("nisvarthastudentrecord.educationrecord.current.status").is("active")),
	 count().as("total")
	 ).as("ActiveTotalDiplomaStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.previous.course").is("Masters").and("nisvarthastudentrecord.educationrecord.previous.status").is("completed")),
	 count().as("total")
	 ).as("CompletedTotalMastersStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.current.course").is("Masters").and("nisvarthastudentrecord.educationrecord.current.status").is("active")),
	 count().as("total")
	 ).as("ActiveTotalMastersStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.previous.course").is("SSLC").and("nisvarthastudentrecord.educationrecord.previous.status").is("completed")),
	 count().as("total")
	 ).as("CompletedTotalSSLCStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.current.course").is("SSLC").and("nisvarthastudentrecord.educationrecord.current.status").is("active")),
	 count().as("total")
	 ).as("ActiveTotalSSLCStudents")
	 .and(match(Criteria.where("nisvarthastudentrecord.educationrecord.current.status").is("transfer")),
	 count().as("total")
	 ).as("TotalVolunteerTransfers")

	 ;

	 

	 ProjectionOperation projectToMatchModel = project()
	 .andExpression("CompletedTotalMedicalStudents.total").as("CompletedTotalMedicalStudents")
	 .andExpression("ActiveTotalMedicalStudents.total").as("ActiveTotalMedicalStudents")
	 .andExpression("CompletedTotalSSLCStudents.total").as("CompletedTotalSSLCStudents")
	 .andExpression("ActiveTotalSSLCStudents.total").as("ActiveTotalSSLCStudents")
	 .andExpression("CompletedTotalEngineeringStudents.total").as("CompletedTotalEngineeringStudents")
	 .andExpression("ActiveTotalEngineeringStudents.total").as("ActiveTotalEngineeringStudents")
	    .andExpression("CompletedTotalPUCStudents.total").as("CompletedTotalPUCStudents")
	 .andExpression("ActiveTotalPUCStudents.total").as("ActiveTotalPUCStudents")
	    .andExpression("CompletedTotalDegreeStudents.total").as("CompletedTotalDegreeStudents")
	 .andExpression("ActiveTotalDegreeStudents.total").as("ActiveTotalDegreeStudents")
	 .andExpression("CompletedTotalDiplomaStudents.total").as("CompletedTotalDiplomaStudents")
	 .andExpression("ActiveTotalDiplomaStudents.total").as("ActiveTotalDiplomaStudents")
	 .andExpression("CompletedTotalMastersStudents.total").as("CompletedTotalMastersStudents")
	 .andExpression("ActiveTotalMastersStudents.total").as("ActiveTotalMastersStudents")
	 .andExpression("TotalVolunteerTransfers.total").as("TotalVolunteerTransfers")  
	 ;
	  


	 Aggregation agg = Aggregation.newAggregation(facetop,projectToMatchModel);
	 //System.out.println("I am here vijay---->>> Query generated"+ agg.toString());
	 AggregationResults<StudentSummaryReport> results = mongoTemplate.aggregate(agg,"students", StudentSummaryReport.class);
	 StudentSummaryReport studentSummarycount = results.getMappedResults().get(0);

	 //////////////BAD CODING need to be fixed when time permits
	 if(studentSummarycount.getCompletedTotalMedicalStudents()==null)
	 {
	 studentSummarycount.setCompletedTotalMedicalStudents(Long.valueOf(0));
	 }
	 if(studentSummarycount.getActiveTotalMedicalStudents()==null)
	 {
	 studentSummarycount.setActiveTotalMedicalStudents(Long.valueOf(0));

	 }

	 if(studentSummarycount.getCompletedTotalSSLCStudents()==null)
	 {
	 studentSummarycount.setCompletedTotalSSLCStudents(Long.valueOf(0));
	 }
	 if(studentSummarycount.getActiveTotalSSLCStudents()==null)
	 {
	 studentSummarycount.setActiveTotalSSLCStudents(Long.valueOf(0));

	 }

	 if(studentSummarycount.getCompletedTotalEngineeringStudents()==null)
	 {
	 studentSummarycount.setCompletedTotalEngineeringStudents(Long.valueOf(0));
	 }
	 if(studentSummarycount.getActiveTotalEngineeringStudents()==null)
	 {
	 studentSummarycount.setActiveTotalEngineeringStudents(Long.valueOf(0));

	 }


	 if(studentSummarycount.getCompletedTotalPUCStudents()==null)
	 {
	 studentSummarycount.setCompletedTotalPUCStudents(Long.valueOf(0));
	 }
	 if(studentSummarycount.getActiveTotalPUCStudents()==null)
	 {
	 studentSummarycount.setActiveTotalPUCStudents(Long.valueOf(0));

	 }

	 if(studentSummarycount.getCompletedTotalDegreeStudents()==null)
	 {
	 studentSummarycount.setCompletedTotalDegreeStudents(Long.valueOf(0));
	 }
	 if(studentSummarycount.getActiveTotalDegreeStudents()==null)
	 {
	 studentSummarycount.setActiveTotalDegreeStudents(Long.valueOf(0));

	 }



	 if(studentSummarycount.getCompletedTotalDiplomaStudents()==null)
	 {
	 studentSummarycount.setCompletedTotalDiplomaStudents(Long.valueOf(0));
	 }
	 if(studentSummarycount.getActiveTotalDiplomaStudents()==null)
	 {
	 studentSummarycount.setActiveTotalDiplomaStudents(Long.valueOf(0));

	 }




	 if(studentSummarycount.getCompletedTotalMastersStudents()==null)
	 {
	 studentSummarycount.setCompletedTotalMastersStudents(Long.valueOf(0));
	 }
	 if(studentSummarycount.getActiveTotalMastersStudents()==null)
	 {
	 studentSummarycount.setActiveTotalMastersStudents(Long.valueOf(0));

	 }



	 if(studentSummarycount.getTotalVolunteerTransfers()==null)
	 {
	 studentSummarycount.setTotalVolunteerTransfers(Long.valueOf(0));
	 }

	 Long totalActive = studentSummarycount.getActiveTotalDegreeStudents() +
	 studentSummarycount.getActiveTotalDiplomaStudents() +
	 studentSummarycount.getActiveTotalEngineeringStudents() +
	 studentSummarycount.getActiveTotalMastersStudents() +
	 studentSummarycount.getActiveTotalMedicalStudents() +
	 studentSummarycount.getActiveTotalPUCStudents() +
	 studentSummarycount.getActiveTotalSSLCStudents();


	 Long totalCompleted = studentSummarycount.getCompletedTotalDegreeStudents() +
	 studentSummarycount.getCompletedTotalDiplomaStudents() +
	 studentSummarycount.getCompletedTotalEngineeringStudents() +
	 studentSummarycount.getCompletedTotalMastersStudents() +
	 studentSummarycount.getActiveTotalMedicalStudents() +
	 studentSummarycount.getCompletedTotalPUCStudents()+
	 studentSummarycount.getCompletedTotalSSLCStudents()+
	 studentSummarycount.getTotalVolunteerTransfers()
	           ;



	 studentSummarycount.setTotalofActiveStudents(totalActive);
	 studentSummarycount.setTotalofCompletedStudents(totalCompleted);

	 //System.out.println("Count ------------------------>>>"+vijaycount.getCompletedTotalPUCStudents());
	 return studentSummarycount;

	 }
	
}
