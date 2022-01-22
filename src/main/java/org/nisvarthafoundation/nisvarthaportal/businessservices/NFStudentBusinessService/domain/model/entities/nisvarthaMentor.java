package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities;

import org.springframework.data.annotation.TypeAlias;

import lombok.Data;

@Data
@TypeAlias("Administration.mentor") 
public class nisvarthaMentor {
	
	private String name;
	private String contactNumber;
	private String emailId;
	private String address1;
	private String district;
	private String state;
	private String status;
	private String datefrom;
	private String dateto;
}
