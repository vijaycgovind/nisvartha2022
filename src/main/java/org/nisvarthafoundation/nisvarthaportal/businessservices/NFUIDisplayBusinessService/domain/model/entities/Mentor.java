package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "mentors")
public class Mentor {
	
	private String Id;
	private String NisvarthaId;
	private String FirstName;
	private String MiddleName;
	private String PrimaryEmail;
	private String SecondaryEmail;
	private String Gender;
	private String HomePhone;
	private String MobilePhone;
	private String Address1;
	private String Address2;
	private String Address3;
	private String City;
	private String District;
	private String State;
	private String Status;
	private String Comments;
	private String CreatedBy;
	private String CreatedDate;
	private String UpdatedBy;
	private String UpdatedDate;
}
