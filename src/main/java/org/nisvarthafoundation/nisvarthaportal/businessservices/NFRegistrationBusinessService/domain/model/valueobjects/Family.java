package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Family {
	
	private String familyAnnualIncome;
	private String currency;
	private String totalFamilyMembers;
	private String documentsPath;
	private String fatherName;
	private String motherName;
	private String fatherOccupation;
	private String motherOccupation;
	private Contact contact;	
	
}
