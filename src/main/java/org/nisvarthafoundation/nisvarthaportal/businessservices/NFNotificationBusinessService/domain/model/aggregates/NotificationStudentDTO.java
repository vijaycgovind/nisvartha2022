package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.aggregates;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NotificationStudentDTO {
	
		@Id
		private String NotificationNumber;
		private String studentNumber;
		private String studentName;
	
		private String emailId;
		private String operation;

}
