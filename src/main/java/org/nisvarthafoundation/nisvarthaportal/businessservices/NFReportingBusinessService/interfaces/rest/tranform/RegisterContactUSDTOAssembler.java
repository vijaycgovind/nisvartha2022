package org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.interfaces.rest.tranform;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.domain.model.operations.commands.RegisterContactUSRequestCommand;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.interfaces.rest.dto.ContactUSDTO;

public class RegisterContactUSDTOAssembler {

	    /**
	     * Static method within the Assembler class
	     * @param bookCargoResource
	     * @return BookCargoCommand Model
	     */
	    public static RegisterContactUSRequestCommand toCommandFromDTO(ContactUSDTO contactUSDTO){

	        return new RegisterContactUSRequestCommand(
	        		contactUSDTO.getName(),
	        		contactUSDTO.getEmail(),
	        		contactUSDTO.getPhone(),
	        		contactUSDTO.getSubject(),
	        		contactUSDTO.getUserinputs()
	        		);
	                                    
	        
	        
	    }
	}
