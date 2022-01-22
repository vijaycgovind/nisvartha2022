package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities;


import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.EducationRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.NisvarthaFinanceRecord;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.NisvarthaMentorRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.NisvarthaStudentRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.Sponsorshiprecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.StudentEducationDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="students")
@TypeAlias("Administration")
public class NisvarthaRecord {	

	private String applicationformnumber;
	private String nisvarthastudentid;
	private NisvarthaStudentRecord nisvarthastudentrecord;
	private NisvarthaMentorRecord nisvarthamentorrecord;
	private NisvarthaFinanceRecord nisvarthafinancerecord;
	private Sponsorshiprecord sponsorshiprecord;	

	}

	
