package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.aggregates;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.Candidate;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.EducationDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Family;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.IdentificationDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Mentor;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.PersonalDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="students")
@Component
public class StudentIDFormDTO {
	
@Id
	private String StudentID;
    private String ApplicationFormNumber;
	private Candidate applicationform;
//	private IdentificationDetails identificationDetails;
//    private Family familyDetails;
//	private PersonalDetails personalDetails;
//	private EducationDetails educationDetails;
	private StudentStatus studentStatus;
//	private Mentor mentor;

}
