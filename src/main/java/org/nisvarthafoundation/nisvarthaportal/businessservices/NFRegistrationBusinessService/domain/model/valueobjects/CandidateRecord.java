package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.User;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection="students")
@TypeAlias("Administration")
public class CandidateRecord {
	

		private String applicationformnumber;
		private String nisvarthastudentid;
		private User nisvarthastudentrecord;
		private NisvarthaStudentDocumentsRecord nisvarthaStudentDocumentRecord;
		private DocumentStatus documentStatus;

		}
