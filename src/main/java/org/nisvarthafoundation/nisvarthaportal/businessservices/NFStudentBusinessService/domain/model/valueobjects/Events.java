package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;


import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "nivarthaEventStore")
@TypeAlias("Events")
public class Events {
	

	private String nisarthaID;
	private String eventID;
	private String createdDate;
	private String createdBy;
	private String domain;
	private String domainEventType;
	private String payload;
	//NisvarthaRecord nisvarthaRecord;
	//UserCredentials userCredentials;
	//Mentor mentor;

}