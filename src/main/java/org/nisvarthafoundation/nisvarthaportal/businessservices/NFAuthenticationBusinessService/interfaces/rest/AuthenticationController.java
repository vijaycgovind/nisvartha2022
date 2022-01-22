package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.interfaces.rest;


import java.security.SecureRandom;
import java.util.List;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.application.internal.commandservices.AuthenticationCommandService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.application.internal.queryservices.AuthenticationQueryService;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects.LoginUser;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects.UserRoles;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects.UserStatus;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.interfaces.rest.dto.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;


	//import io.swagger.annotations.Api;
    @CrossOrigin(origins = { "http://localhost:4200" })
	@Controller    // This means that this class is a Controller
	@RequestMapping("/Authentication")
	@Api(value = "Authentication",  description = " Endpoints for nisvartha Authentication", tags = "Authentication Commands")
	public class AuthenticationController {

	
		@Autowired
	    private AuthenticationQueryService authenticationQueryService;
	 			
		@PostMapping(path = "/isValid")	
		@ResponseBody
	    public UserStatus isAValidUser(@RequestBody LoginUserDTO loginUserDTO) {
		
				UserStatus contuserStatus = new UserStatus();
			    contuserStatus = authenticationQueryService.isAValidUser(loginUserDTO);
			    return contuserStatus;

		}	
		

		@GetMapping(path = "/validateTAFUser")	
		@ResponseBody
		@ResponseStatus(value = HttpStatus.ACCEPTED)
	    public boolean isAValidTAFUser(@RequestBody LoginUserDTO loginUserDTO) {
		    return authenticationQueryService.isAValidTAFUser(loginUserDTO);
		}
		
		
		
		
	    @GetMapping("/findAllRoles")
	    @ResponseBody
	    public List<UserRoles> getAllRolesNF(){
	    	
	    	System.out.println("Hello inside ctrl auth");
	    return authenticationQueryService.findAllRolesNF();
	    }
	      
	
	
	    
	    
	}

