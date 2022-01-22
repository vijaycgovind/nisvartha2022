package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.aggregates;

import org.bson.types.Binary;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.User;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.DocumentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects.Documents;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection="registration")
@TypeAlias("CandidateRegistrationForm")
public class ApplicationForm {
	
	@Id
    private String applicationFormNumber;
	private User applicationform;
	private String applicationStatus;
	private String applicationYear;
	private Documents documentsSubmitted;
	private DocumentStatus documentStatus;
	private String updatedBy;
	private String updatedDate;
	private String comments;
	
    public ApplicationForm( String applicationFormNumber,User applicationform,String applicationStatus,String applicationYear,Documents documentsSubmitted,DocumentStatus documentStatus)
    {
    
    this.applicationFormNumber=applicationFormNumber;
    this.applicationform=applicationform;
    this.applicationStatus= applicationStatus;
    this.applicationYear=applicationYear;
    this.documentsSubmitted=documentsSubmitted;
    this.documentStatus=documentStatus;
    
    }
    

}