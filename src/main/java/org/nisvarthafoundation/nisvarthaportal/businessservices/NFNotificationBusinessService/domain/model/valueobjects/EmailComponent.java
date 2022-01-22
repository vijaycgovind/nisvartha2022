package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.valueobjects;

import lombok.Data;

@Data
public class EmailComponent {
	
	private String emailTo;
	private String templateFlag;
	private String recepientName;

}
