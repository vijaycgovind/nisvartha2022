package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.interfaces.rest.tranform;


import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.operations.commands.StudentCreateCommand;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.createStudent;

public class createStudentAssembler {

	
   public static StudentCreateCommand toCommandFromDTO(createStudent createstudeentdata){
   	
	   System.out.println("In the assembler dto================the value of createStudent DTO IS::" + createstudeentdata.toString() );

  	return new StudentCreateCommand(createstudeentdata.getNisvarthaTempTAFID(),createstudeentdata.getComments(),createstudeentdata.getUpdatedBy());
    }


}
