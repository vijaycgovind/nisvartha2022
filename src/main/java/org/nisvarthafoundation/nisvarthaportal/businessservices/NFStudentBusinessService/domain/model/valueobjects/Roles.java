package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "roles")

public class Roles {
	
	private String role_id;
	private String role;
	private String updatedBy;
	private String updatedDate;
	
}
