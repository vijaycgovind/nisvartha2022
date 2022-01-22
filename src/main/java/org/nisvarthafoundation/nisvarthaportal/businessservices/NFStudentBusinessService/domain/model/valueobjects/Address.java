package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import lombok.Data;

@Data
public class Address {
	
	private String address;
	private String addressContinued;
	private String city;
	private String district;
	private String state;
	private String pinCode;	

}
