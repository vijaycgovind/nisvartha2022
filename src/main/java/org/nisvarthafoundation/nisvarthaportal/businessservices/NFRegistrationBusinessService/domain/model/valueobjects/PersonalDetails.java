package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalDetails {
	
	private String studentName;
	private String photo;
	private String caste;
	private String religion;
	private int age;
	private String sex;
	private String dateofBirth;
	private Contact contact;
	private Family family;
	
	
	
	


}
