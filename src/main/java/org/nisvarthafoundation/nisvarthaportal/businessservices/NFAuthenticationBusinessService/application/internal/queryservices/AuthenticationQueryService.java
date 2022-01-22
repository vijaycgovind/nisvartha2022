package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.application.internal.queryservices;


import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects.UserRoles;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects.UserStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.interfaces.rest.dto.LoginUserDTO;

public interface AuthenticationQueryService {
	
	public UserStatus isAValidUser(LoginUserDTO userName);
	
	public boolean isAValidTAFUser(LoginUserDTO userName);
	
	
	public List<UserRoles> findAllRolesNF();

	
		
}
