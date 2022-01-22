package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.EducationDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Family;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.IdentificationDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Mentor;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.PersonalDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "regcandidates")
@Component
@TypeAlias("Registration")
// @Builder
public class Candidate {
	
	@Id
	private String id;
	private String studentName;
	private String applicationFormNumber;
	private IdentificationDetails identificationDetails;
    private Family familyDetails;
	private PersonalDetails personalDetails;
	private EducationDetails educationDetails;
	private StudentStatus studentStatus;


}
