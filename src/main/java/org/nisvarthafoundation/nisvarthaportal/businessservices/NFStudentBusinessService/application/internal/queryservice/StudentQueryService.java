package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.queryservice;

import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.aggregates.StudentIDFormDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.operations.commands.StudentUserCommand;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentSummaryReport;

public interface StudentQueryService {

    public long getCountofStudents();
	public long getStudentsCountByCity(String city);
	public long getStudentscountByStatus(String status);
	public NisvarthaRecord getStudentsdetailsByID(String studentNumber);
	long getCountOfStudentsByCurrentCourse(String currentCourse, String currentStatus);
	public long getNisvarthaStudentsCount();
	public StudentSummaryReport getStudentSummary();
	
	
	
}
