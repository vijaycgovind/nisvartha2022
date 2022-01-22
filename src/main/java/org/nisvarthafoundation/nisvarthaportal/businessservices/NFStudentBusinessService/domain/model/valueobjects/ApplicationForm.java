package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;



import org.springframework.data.annotation.Id;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationForm {
	
	@Id
    private String applicationFormNumber;
	private User applicationform;
	private String applicationStatus;
	private String applicationYear;
	private Documents documentsSubmitted;
	private DocumentStatus documentStatus;
	
 

}