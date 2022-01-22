package org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.infrastructure.repositories.JPA;

import org.nisvarthafoundation.nisvarthaportal.businessservices.NFAuthenticationBusinessService.domain.model.valueobjects.LoginUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;

public interface userValidationRepository extends MongoRepository<LoginUser, String>{
	
	  LoginUser findByuserName(String userName);
}