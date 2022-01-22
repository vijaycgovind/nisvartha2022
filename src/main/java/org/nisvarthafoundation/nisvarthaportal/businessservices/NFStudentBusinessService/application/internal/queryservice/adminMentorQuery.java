package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.queryservice;

import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Mentor;

public interface adminMentorQuery {
	
    public long getCountofMentors();
    
    public long getCountofMentorsByCity(String city);
    
    public List<Mentor> getMentorDetailsByStudent (String studentName);
    
    public List<Mentor> getMentorList();
    
    public List<Mentor> getMentorEmailAddress();
    
    public void studentMentorAssign(String studentName, String mentorName, String nisvarthastudentid, String updatedBy);

    public void deleteMentorbyID(String studentId );
}
