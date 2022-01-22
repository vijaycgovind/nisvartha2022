package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest.dto;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.NisvarthaFinanceRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.NisvarthaMentorRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.NisvarthaStudentRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.Sponsorshiprecord;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="students")
public class NisvarthaStudentAdmissionProcessDTO {
	
	 private String tenthPecerntageCutoff;
	 private String pucPercentageCutoff;
	 private String lastDayForSubmission;
	 private String currentYear;
	 private String updatedBy;
	 private String updatedDate;

}
