package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.operations.commands;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.NisvarthaFinanceRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.NisvarthaMentorRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.NisvarthaStudentRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.valueobjects.Sponsorshiprecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministrationUserCommand {
	
	
	private String applicationformnumber;
	private String nisvarthastudentid;
	private NisvarthaStudentRecord nisvarthastudentrecord;
	private NisvarthaMentorRecord nisvarthamentorrecord;
	private NisvarthaFinanceRecord nisvarthafinancerecord;
	private Sponsorshiprecord sponsorshiprecord;


}