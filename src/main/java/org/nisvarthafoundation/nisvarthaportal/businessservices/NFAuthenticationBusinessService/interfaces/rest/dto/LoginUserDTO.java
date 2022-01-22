package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.interfaces.rest.dto;

import java.util.ArrayList;


import lombok.Data;

	
@Data
public class LoginUserDTO {
	
	private String id;
	private String userName;
	private String password;	
	private String active;
	private String updatedBy;
	private String updatedDate;

    private ArrayList <String> roles;
	
}


