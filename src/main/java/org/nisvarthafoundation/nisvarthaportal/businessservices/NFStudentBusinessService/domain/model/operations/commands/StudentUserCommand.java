package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.operations.commands;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentUserCommand {

	private String applicationFormNumber;
    private String studentNumber;
	private NisvarthaRecord studentDetails;
	private StudentStatus studentStatus;
	

	
}
