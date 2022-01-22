package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.application.internal.queryservices;

import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaFlashMessage;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.domain.model.entities.NisvarthaStudentAdmissionProcessRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAdministrationBusinessService.interfaces.rest.dto.NisvarthaStudentAdmissionProcessDTO;


public interface AdministrationQueryService {
	

	// list all nisvartha studetns those who have been approved by management
		public List<NisvarthaRecord> getAllNisvarthaStudents();
		public NisvarthaRecord getNisvarthaStudentProvidedNisvarthaID(String nisvarthaid);
		public NisvarthaStudentAdmissionProcessRecord getStudentAdmissionProcessDetails();
		public NisvarthaFlashMessage getActiveFlashMessage();

}
