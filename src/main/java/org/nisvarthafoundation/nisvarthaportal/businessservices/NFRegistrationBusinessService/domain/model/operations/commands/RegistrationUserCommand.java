package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.operations.commands;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.User;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.DocumentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.Documents;

import lombok.Data;

@Data
public class RegistrationUserCommand {
	
	
    private String applicationFormNumber;
	private User applicationform;
	private String applicationStatus;
	private Documents documents;
	private DocumentStatus documentStatus;
	
	
	public RegistrationUserCommand(String applicationFormNumber, User applicationform,String applicationStatus,Documents documents,DocumentStatus documentStatus) {
		super();
		this.applicationFormNumber = applicationFormNumber;
		this.applicationStatus=applicationStatus;
		this.applicationform = applicationform;
		this.documents=documents;
		this.documentStatus=documentStatus;
		
	}
	




}
