package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest.tranform;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.operations.commands.AdministrationUserCommand;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest.dto.NisvarthaRecordDTO;


public class AdministrationDTOAssembler {

	
    public static AdministrationUserCommand toCommandFromDTO(NisvarthaRecordDTO dto){
    	
    	System.out.println("In the administration dto================the value of STUDENTE************** DTO IS::" + dto.toString() );

        return new AdministrationUserCommand(dto.getApplicationformnumber(),
        		dto.getNisvarthastudentid(),
        		dto.getNisvarthastudentrecord(),
        		dto.getNisvarthamentorrecord(),
        		dto.getNisvarthafinancerecord(),
        		dto.getSponsorshiprecord()
        		);
                                    
    	
    }
}
