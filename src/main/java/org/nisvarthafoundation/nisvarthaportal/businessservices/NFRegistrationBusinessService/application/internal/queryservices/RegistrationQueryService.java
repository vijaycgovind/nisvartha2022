package org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.application.internal.queryservices;

import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.aggregates.ApplicationForm;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFRegistrationBusinessService.domain.model.entities.LoginUser;


public interface RegistrationQueryService {
	
	public ApplicationForm getTempApplicationFormDetails(String applicationNumber);
	public ApplicationForm getAllDataOfTempApplicationFormDetails(String applicationNumber);
	public boolean isAValidNFTAN(LoginUser validateLoginUserDetails);
	public long getCountofApplicationForms();
	public long getNisvarthaStudentsCount();
	public long getPendingFormsCount();
	public long getPartialFormsCount();
	public List<ApplicationForm> getPendingFormsList();

}
