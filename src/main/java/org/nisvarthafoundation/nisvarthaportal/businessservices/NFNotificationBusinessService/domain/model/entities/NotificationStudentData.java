package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="notification")

public class NotificationStudentData {

	@Id
	private String NotificationNumber;
	private String studentNumber;
	private String Name;
	private String emailId;
	private String operation;

}
