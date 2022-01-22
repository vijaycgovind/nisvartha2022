package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.interfaces.rest.dto;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.Candidate;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class studentDTO {
	
	    private String studentNumber;
		private Candidate studentData;
		private StudentStatus studentStatus;


}
