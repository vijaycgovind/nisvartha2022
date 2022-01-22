package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "associateMembers")
public class Member {
	
	private String id;
	private String index;
	private String name;
	private String introducedBy;
	private String email;

}
