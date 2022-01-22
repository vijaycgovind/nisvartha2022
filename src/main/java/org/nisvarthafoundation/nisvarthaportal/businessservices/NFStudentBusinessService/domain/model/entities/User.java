package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities;


import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.EducationDetails;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Family;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.IdentificationDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.PersonalDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class User {
	
//	@Id
//	private String id;
    private IdentificationDetails identificationDetails;
    private Family familyDetails;
	private PersonalDetails personalDetails;
	private EducationDetails educationDetails;	
	
}