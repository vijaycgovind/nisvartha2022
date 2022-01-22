package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.tranform;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.operations.commands.RegistrationUserCommand;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.operations.commands.UpdateCandidateStatusCommand;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.dto.ApplicationFormDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.interfaces.rest.dto.UpdateCandiateStatus;

public class RegistrationDTOAssembler {

	
	    public static RegistrationUserCommand toCommandFromDTO(ApplicationFormDTO applicationFormDTO){
	    	
	    
	        return new RegistrationUserCommand(applicationFormDTO.getApplicationFormNumber(),
	        		applicationFormDTO.getApplicationform(),
	        		applicationFormDTO.getApplicationStatus(),
	        		applicationFormDTO.getDocumentsSubmitted(),
	        		applicationFormDTO.getDocumentStatus());
	    }
	    
	    public static UpdateCandidateStatusCommand toCommandFromDTO(UpdateCandiateStatus updateCandiateStatus){
	    	
		    
	        return new UpdateCandidateStatusCommand(updateCandiateStatus.getCandiateTANumber());
	    }
	    
	   
	}
