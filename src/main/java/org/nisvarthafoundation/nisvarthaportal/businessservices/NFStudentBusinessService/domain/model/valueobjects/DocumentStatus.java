package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import org.bson.types.Binary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentStatus {
	

	private String casteProofSubmitted;
	private String incomeProofSubmitted;
	private String passbookProofSubmitted;
	private String housephotoProofSubmitted;
	private String studentwriteupProofSubmitted;
	private String parentwriteupProofSubmitted;
	private String bplcardProofSubmitted;
	private String markscardProofSubmitted;
	private String signedapplicationformProofSubmitted;

}
