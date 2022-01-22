package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.operations.commands;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.entities.NotificationStudentData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class NotificationUserCommand {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Document(collection="notification")

	public class NotificationStudentData {

		@Id
//		private String NotificationNumber;
		private String studentNumber;
		private String FirstName;
		private String Lastname;
		private String emailId;
		private String operation;
	}	
	
}
