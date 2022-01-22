package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetails {
	
	private String studentName;
	private String photo;
	private String caste;
	private String religion;
	private String age;
	private String sex;
	private String dateofBirth;
	private Contact contact;
	private Family family;
	
	
	
	


}
