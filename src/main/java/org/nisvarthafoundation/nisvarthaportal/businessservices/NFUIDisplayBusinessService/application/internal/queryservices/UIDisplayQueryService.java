package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.application.internal.queryservices;


import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Corporate;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.District;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Donor;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Member;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Mentor;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Occupation;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.Patron;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.State;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.entities.StudentData;


public interface UIDisplayQueryService {
	
	// method to check in which states nisvartha is operating out of 36 states in India - for UI display in ourwork page - return count of states
	public long findByOperationalStates();
	
	// method to check in which states nisvartha is operating out of 36 states in India - for UI display in ourwork page - return count of states
	public List<State> findAllStates();
	
	// method to corporate count supporting nisvarhta foundation
	public long findCountOfCorporateSupportingNF();
		
	// method to get all Corporate details supporting nisvartha foundation
	public List<Corporate> findAllCorporateSupportingNF();
	
	// method to patron count supporting nisvarhta foundation
	public long findCountOfPatronSupportingNF();
		
	// method to get all Patron details supporting nisvartha foundation
	public List<Patron> findAllPatronsSupportingNF();
	
	// method to Donor count supporting nisvarhta foundation
	public long findCountOfDonorSupportingNF();
		
	// method to get all Donor details supporting nisvartha foundation
	public List<Donor> findAllDonorsSupportingNF();
	
	// method to get all districts
	public List<District> findAllDistricts();
	
	// method to get all districts
		public List<Mentor> findAllMentorsforUIdisplay();
		
		public List<String> findAllMentorsbyName();
	
	// method to get all Assd. members details supporting nisvartha foundation
	public List<Member> findAllMembersSupportingNF();
		
		// method to get count of associated members
	public long findCountOfMembersSupportingNF();

	public List<Occupation> findAllOccupation();

	public long findCountofMentorsSupportingNF();

	//Method to list students for UI display
	public List<StudentData> findAllStudentsforUIdisplay();
	


	
}
