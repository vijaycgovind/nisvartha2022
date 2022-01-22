package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.operations.commands;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NisvarthaFlashMessageCommand {
	
	private String flashMessage;
	private String updatedBy;
	private String updatedDate;
	private String status;

}
