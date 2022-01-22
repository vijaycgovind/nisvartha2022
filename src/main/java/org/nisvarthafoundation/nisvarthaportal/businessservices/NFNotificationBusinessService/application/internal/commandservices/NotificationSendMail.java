package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.application.internal.commandservices;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.aggregates.NotificationStudentDTO;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.operations.commands.NotificationUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class NotificationSendMail {
	
	@Autowired
    private JavaMailSender emailSender;

	public void sendMail(NotificationUserCommand notificationUserCommand) {
		// Save data in Notification table
		// trigger mail
		
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("champaka.tn@gmail.com", "vijaycgovind@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

    //    Cmd cmd = new Cmd();
        // JavaMailSenderImpl.send(msg);
         
         emailSender.send(msg);
        
	//	return "success";
	}

}
