package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "credentials")
@TypeAlias("createUser")
public class UserCredentials {
	
	private String id;
	private String nisvarthaId;
	private String userName;
	private String password;
	private String active;
	private String updatedBy;
	private String updatedDate;
	private String roles[];
}
