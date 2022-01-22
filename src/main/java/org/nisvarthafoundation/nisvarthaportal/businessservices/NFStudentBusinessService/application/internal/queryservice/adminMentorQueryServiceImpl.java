package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.queryservice;



import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class adminMentorQueryServiceImpl implements adminMentorQuery{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    public long getCountofMentors()  {
    	System.out.println("Get Cunt of Mentors - Service Imple");
    Query query = new Query();
    long test = mongoTemplate.count(query, Mentor.class);
    
    return test ; 
    }
    
    public long getCountofMentorsByCity(String mcity)  {
    	System.out.println("Get count of Mentors by City - Service Impl");
    Query query = new Query();
    
    query.addCriteria(Criteria.where("city").is(mcity));
    
    long test = mongoTemplate.count(query, Mentor.class);
    
    return test ; 
    }
    // List mentor by student
    // delete mentor
    
    public List<Mentor> getMentorDetailsByStudent (String studentName) {
    	
    	System.out.println("Get count of Mentors by City - Service Impl");
    	
    	List<Mentor> mentorListByStudent = (List<Mentor>) new Mentor(); 
    	Query query = new Query();
        
        query.addCriteria(Criteria.where("studentname").is(studentName));
        return (mongoTemplate.find(query, Mentor.class));
    }
    
    public List<Mentor> getMentorList(){
    	
    	System.out.println("Inside Query Services .....");
    	
    	List<Mentor> mentorList = mongoTemplate.findAll(Mentor.class);
    	
    	return mentorList;
    }
    
    
   public List<Mentor> getMentorEmailAddress(){
    	
    	System.out.println("Inside Email Services .....");
    	
    	Query query = new Query();
    	query.addCriteria(Criteria.where("status").is("active"));
		query.fields().include("mentorname");
		query.fields().include("email");
    	query.fields().exclude("_id");
		List<Mentor> mentorList = mongoTemplate.find(query,Mentor.class);
    	
		
		
    	return mentorList;
    }

	@Override
	public void studentMentorAssign(String studentName, String mentorName, String nisvarthastudentid,
			String updatedBy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMentorbyID(String studentId) {
		// TODO Auto-generated method stub
		
	}
    

}
