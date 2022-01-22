package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.valueobjects.EducationDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.valueobjects.Family;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.valueobjects.IdentificationDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.valueobjects.PersonalDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.valueobjects.StudentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
@Component
// @Builder
public class StudentData {
	
	@Id
	private String studentId;
	private String studentFirstName;
	private String studentLastName;
	private String applicationFormNumber;
	private IdentificationDetails identificationDetails;
    private Family familyDetails;
	private PersonalDetails personalDetails;
	private EducationDetails educationDetails;
	private StudentStatus studentStatus;
	private Mentor mentorAssign;

}
