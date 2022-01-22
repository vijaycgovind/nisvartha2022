package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.DocumentStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Documents;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaFinanceRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.NisvarthaMentorRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Sponsorshiprecord;
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

	
	@Id
	private String nisvarthastudentid;
    private String nisvarthatempstudentid;
    private String oldStudentNumber;
    private String applicationStatus;
	private String applicationYear;
	// This flag is for tracting if the student is active or in-active in the system
	private String studentActivestatus;
	private String updatedBy;
	private String updatedDate;
	private String comments;
	private User applicationform;
	private Documents documentsSubmitted;
	private DocumentStatus documentStatus;
	private NisvarthaMentorRecord nisvarthamentorrecord;
	private NisvarthaFinanceRecord nisvarthafinancerecord;
	private Sponsorshiprecord sponsorshiprecord;
	
	
	}

	
