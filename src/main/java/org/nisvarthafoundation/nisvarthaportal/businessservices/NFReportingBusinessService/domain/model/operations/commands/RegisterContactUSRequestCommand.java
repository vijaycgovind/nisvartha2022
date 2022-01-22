package org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.domain.model.operations.commands;

import lombok.Data;

@Data
public class RegisterContactUSRequestCommand {
	
	private String Id;
	private String name;
	private String email;
	private String phone;
	private String subject;
	private String userinputs;
	
	public RegisterContactUSRequestCommand(String name, String email, String phone, String subject, String userinputs) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.subject = subject;
		this.userinputs = userinputs;
	}
	
	

}
