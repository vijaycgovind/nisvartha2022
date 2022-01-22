package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.interfaces.rest.transform;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.aggregates.NotificationStudentDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.operations.commands.NotificationUserCommand;

public class NotificationStudentDTOAssembler {
	
    public static NotificationUserCommand toCommandFromDTO(NotificationStudentDTO notificationStudentDTO){
    	

    	return new NotificationUserCommand(
//    			notificationStudentDTO.getStudentNumber(),
//    			notificationStudentDTO.getFirstName(),
//    			notificationStudentDTO.getLastname(),
//    			notificationStudentDTO.getEmailId(),
//    			notificationStudentDTO.getOperation()
    			);
    }

}
