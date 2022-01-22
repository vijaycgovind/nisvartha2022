package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "donors")
public class Donor {
	
	    private String id;
		private String index;
		private String date;
		private String name;
		private String introducedBy;
		private String pan;
		private String phone;
		private String contribution;
		private String email;
		private String company;
		private String designation;
		private String fatherName;
		private String DOB;
		private String address;

}
