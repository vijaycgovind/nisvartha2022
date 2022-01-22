package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.dto;


import org.bson.types.Binary;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.User;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.DocumentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.Documents;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApplicationFormDTO {
	
	private String applicationFormNumber;
	private User applicationform;
	private String applicationStatus;
	private String applicationYear;
	private Documents documentsSubmitted;
	private DocumentStatus documentStatus;
	private String updatedBy;
	private String updatedDate;
	private String comments;

}