package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.application.internal.queryservice;

import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.NisvarthaRecord;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.entities.User;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.Roles;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects.UserCredentials;


public interface AdministrationQueryService {
	

	// list all nisvartha studetns those who have been approved by management
		public List<NisvarthaRecord> getAllNisvarthaStudents();
		public NisvarthaRecord getNisvarthaStudentProvidedNisvarthaID(String nisvarthaid);
		
		public List<UserCredentials> getUserCredentials();
		
		public List<Roles> getUserroles();
		
		public List<User> getPendingRegistrations();
		
		public Long getPendingRegistrationsCount();

}
