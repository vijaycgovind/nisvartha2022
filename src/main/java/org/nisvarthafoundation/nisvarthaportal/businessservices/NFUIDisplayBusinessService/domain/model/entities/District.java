package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities;

import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;


	
	@Data
	@Document(collection = "districts")
	public class District
	{
		
		private String id;
		private String name;
		
	}

