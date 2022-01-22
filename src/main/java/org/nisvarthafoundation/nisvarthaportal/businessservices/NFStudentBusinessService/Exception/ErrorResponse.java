package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.Exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@XmlRootElement(name="error")
public class ErrorResponse {
	
	private String message;
	
	private List<String> details; 
	
}
