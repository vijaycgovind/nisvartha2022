package org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.domain.model.entities;


import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "contactus")
@TypeAlias("ContactUS")
public class ContactUS {
	
	private String Id;
	private String name;
	private String email;
	private String phone;
	private String subject;
	private String userinputs;
	private String createdDate;
	


}
