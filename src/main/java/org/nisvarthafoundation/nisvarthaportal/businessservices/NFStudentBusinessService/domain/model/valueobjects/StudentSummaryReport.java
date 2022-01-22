package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSummaryReport {
	
	private Long CompletedTotalMedicalStudents;
	private Long ActiveTotalMedicalStudents;
	private Long CompletedTotalSSLCStudents;
	private Long ActiveTotalSSLCStudents;
	private Long CompletedTotalEngineeringStudents;
	private Long ActiveTotalEngineeringStudents;
	private Long CompletedTotalPUCStudents;
	private Long ActiveTotalPUCStudents;
	private Long CompletedTotalDegreeStudents;
	private Long ActiveTotalDegreeStudents;
	private Long CompletedTotalDiplomaStudents;
	private Long ActiveTotalDiplomaStudents;
	private Long CompletedTotalMastersStudents;
	private Long ActiveTotalMastersStudents;
	private Long TotalVolunteerTransfers;
	private long TotalofActiveStudents;
	private long TotalofCompletedStudents;
	
	

}
