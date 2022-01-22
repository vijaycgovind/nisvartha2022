package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.domain.model.valueobjects;

import java.time.LocalDateTime;



import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class Mentor {
	private String mentorname;
	private String email;

}
