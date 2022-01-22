package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.valueobjects;

import org.bson.types.Binary;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documents {
	
	private Binary casteProof;
	private Binary incomeProof;
	private Binary passbookProof;
	private Binary housephotoProof;
	private Binary studentwriteupProof;
	private Binary parentwriteupProof;
	private Binary bplcardProof;
	private Binary markscardProof;
	private Binary signedapplicationformProof;
	


}
