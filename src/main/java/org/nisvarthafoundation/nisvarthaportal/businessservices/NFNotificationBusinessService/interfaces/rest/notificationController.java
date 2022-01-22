package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.interfaces.rest;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.application.internal.commandservices.NotificationSendMail;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.aggregates.NotificationStudentDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.interfaces.rest.transform.NotificationStudentDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/oldnotification")
@RestController
public class notificationController {

	
	@Autowired NotificationSendMail notificationSendMail;
	
	@GetMapping("/sendmail")
	public String sendNotificationToStudent() {

		// String result;
		System.out.println("INSide oldNoti .. sendmail \n");
		
		NotificationStudentDTO notificationStudentDTO = new NotificationStudentDTO();
		
		notificationStudentDTO.setNotificationNumber("AA123");
		notificationStudentDTO.setStudentNumber("NFPAN121212");
		notificationStudentDTO.setEmailId("don@yahoo.com");
		notificationStudentDTO.setStudentName("Don");

		notificationStudentDTO.setOperation("add");
		
		notificationSendMail.sendMail(NotificationStudentDTOAssembler.toCommandFromDTO(notificationStudentDTO));
		
		return "SENDMAIL --- Notification sent";
	}
	
	@GetMapping("/hi")
	public String hello() {

		// String result;
		
		System.out.println("INSIDE NOTICONTROLLER");		
		return "Notification sent";
	}
}
