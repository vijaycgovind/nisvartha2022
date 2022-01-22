package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NisvarthaStudentRecord {
	private IdentificationDetails identificationDetails;
	private String fathername;
	private String fatherstatus;
	private String mothername;
	private String motherstatus;
	private String primaryphone;
	private String secondaryphone;
	private String emailid;
	private Address imaryaddress;
	private Address secondaryaddress;
	private String studentname;
	private String photo;
	private String religion;
	private String caste;
	private String age;
	private String sex;
	private String dateofbirth;	
	private EducationRecord educationrecord;
	//private NFStudentDocumentRecords studentdocumentrecord;
	}
