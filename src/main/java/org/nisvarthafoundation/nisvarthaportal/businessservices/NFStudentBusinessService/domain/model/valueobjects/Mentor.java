package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import java.time.LocalDateTime;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "mentors")
@TypeAlias("Administration")
public class Mentor {
	
	private String id;
	private String nisvarthamentorId;
	private String mentorname;
	private String email;
	private String gender;
	private String mobilePhone;
	private String homePhone;
	private String address1;
	private String address2;
	private String city;
	private String district;
	private String state;
	private String status;
	private String comments;
	private String updatedBy;
	private String updatedDate;
	private String activeDatefrom;
	private String activeDateTo;
}
