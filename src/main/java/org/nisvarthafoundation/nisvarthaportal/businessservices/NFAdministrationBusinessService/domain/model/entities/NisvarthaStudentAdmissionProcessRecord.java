package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="studentAdmissionProcess")
@TypeAlias("Administration")
public class NisvarthaStudentAdmissionProcessRecord {

	@Id 
	 private ObjectId _id;
	 private String status;
	 private String tenthPecerntageCutoff;
	 private String pucPercentageCutoff;
	 private String lastDayForSubmission;
	 private int currentYear;
	 private String updatedBy;
	 private String updatedDate;

}
