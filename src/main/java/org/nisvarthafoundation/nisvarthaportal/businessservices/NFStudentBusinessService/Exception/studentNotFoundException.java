package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class studentNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public studentNotFoundException(String exception) {
		super(exception);
	}

}
