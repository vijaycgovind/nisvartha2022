package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects;

import java.util.ArrayList;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "credentials")
public class LoginUser {
	
	private String id;
	private String userName;
	private String password;	
	private String active;
	private String updatedBy;
	private String updatedDate;
	
	private Boolean nfSuperadmin;
	private Boolean nfAdmin;
	private Boolean nfFinance;
	private Boolean nfMentor;
	private Boolean nfleadMentor;
	private Boolean nfCorporate;
	
    private ArrayList <String> roles;
	
}
