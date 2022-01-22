package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;


import lombok.Data;

@Data
public class Contact {
	
	private Phone phone;
	private String emailId;
	private Address address;
	
	
}
