package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest.dto.NisvarthaRecordDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEducationDetails {
	private String course;
	private String accademicyear;
	private String semester;
	private String status;

}
