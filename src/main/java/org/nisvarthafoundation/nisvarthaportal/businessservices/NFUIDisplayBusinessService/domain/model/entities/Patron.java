package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "patrons")
public class Patron {
	
	private String id;
	private String index;
	private String name;
	private String introducedBy;
	private String doj;
	private String email;
	private String pan;
	private String phoneNumber;
	private String contribution;
	private String company;
	private String designation;
	private String photo;
	private String nameofFather;
	private String dob;
	private String buildingNumber;
	private String buildingName;
	private String street;
	private String area;
	private String city;
	private String district;
	private String state;
	private String pinCode;
	private String education;
	private String briefNote;
	
}