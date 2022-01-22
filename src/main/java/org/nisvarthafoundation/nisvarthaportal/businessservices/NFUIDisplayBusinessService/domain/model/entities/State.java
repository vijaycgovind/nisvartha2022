package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities;

import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;


	
	@Data
	@Document(collection = "states")
	public class State {
		
		private String id;
		private String name;
		private String rank;
		private String operational;
		
	}

