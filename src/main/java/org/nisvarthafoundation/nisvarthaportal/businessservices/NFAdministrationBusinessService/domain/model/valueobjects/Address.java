package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	private String address;
	private String addressContinued;
	private String city;
	private String district;
	private String state;
	private String pinCode;	

}
