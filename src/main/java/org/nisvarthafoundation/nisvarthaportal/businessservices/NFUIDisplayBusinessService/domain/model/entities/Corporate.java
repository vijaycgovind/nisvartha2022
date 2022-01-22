package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "corporates")
public class Corporate {
	
	private String Id;
	private String DOJ;
	private String name;
	private String introducedBy;
	private String emailId;
	
}