package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "mentorstudent")
public class mentorstudent {
	
		private String Id;
		private String mentorName;
		private String studentName;


}
