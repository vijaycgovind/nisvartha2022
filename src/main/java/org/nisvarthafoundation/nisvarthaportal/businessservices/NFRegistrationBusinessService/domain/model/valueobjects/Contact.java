package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {
	
	private Phone phone;
	private String emailId;
	private Address address;
	
	
}
