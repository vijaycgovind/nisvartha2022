package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import org.bson.types.Binary;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documents {
	
	@JsonDeserialize(using = BinaryDeserializer.class)
	private Binary casteProof;
	@JsonDeserialize(using = BinaryDeserializer.class)
	private Binary incomeProof;
	@JsonDeserialize(using = BinaryDeserializer.class)
	private Binary passbookProof;
	@JsonDeserialize(using = BinaryDeserializer.class)
	private Binary housephotoProof;
	@JsonDeserialize(using = BinaryDeserializer.class)
	private Binary studentwriteupProof;
	@JsonDeserialize(using = BinaryDeserializer.class)
	private Binary parentwriteupProof;
	@JsonDeserialize(using = BinaryDeserializer.class)
	private Binary bplcardProof;
	@JsonDeserialize(using = BinaryDeserializer.class)
	private Binary markscardProof;
	@JsonDeserialize(using = BinaryDeserializer.class)
	private Binary signedapplicationformProof;
}
