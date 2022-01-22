package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class UserStatus {

	private String status;
	
	private Boolean nfSuperadmin;
	private Boolean nfAdmin;
	private Boolean nfFinance;
	private Boolean nfMentor;
	private Boolean nfleadMentor;
	private Boolean nfCorporate;
	
	
}