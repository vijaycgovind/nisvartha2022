package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.NFRegistrationExceptions;


import lombok.Data;

@Data
public class ErrorObject {
	
	private Integer statusCode;
	private String message;
	private long timestamp;

}
