package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StudentStatus {
	
	private String nfStatus;
	private String currentStatus;
	private String updatedBy;
	private String updatedDate;
	
}
