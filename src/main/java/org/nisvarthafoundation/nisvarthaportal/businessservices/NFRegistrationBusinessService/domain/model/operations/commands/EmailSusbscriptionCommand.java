package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.operations.commands;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailSusbscriptionCommand {
	
		
	private String firstName;
	private String lastName;
	private String emailId;	
	
	
	
//	public EmailSusbscriptionCommand(String firstName, String lastName, String emailId) {
//		super();		
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.emailId= emailId;
//	}
}

