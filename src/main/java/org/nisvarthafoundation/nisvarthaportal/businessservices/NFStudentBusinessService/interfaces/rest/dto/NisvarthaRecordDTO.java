package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.interfaces.rest.dto;


import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.DocumentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.IdentificationDetails;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaFinanceRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaMentorRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaStudentDocumentsRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaStudentRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Sponsorshiprecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor

public class NisvarthaRecordDTO {
	
	private String applicationformnumber;
	private String nisvarthastudentid;
	private NisvarthaStudentRecord nisvarthastudentrecord;
	private NisvarthaMentorRecord nisvarthamentorrecord;
	private NisvarthaFinanceRecord nisvarthafinancerecord;
	private Sponsorshiprecord sponsorshiprecord;
	private StudentStatus studentStatus;
	private NisvarthaStudentDocumentsRecord nisvarthaStudentDocumentsRecord;
	private DocumentStatus documentStatus;

}