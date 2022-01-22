package org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.application.internal.commandservices;

import java.text.SimpleDateFormat;




import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.RandomStringUtils;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.domain.model.entities.ContactUS;
import org.nisvarthafoundation.nisvarthaportal.businessservices.NFReportingBusinessService.domain.model.operations.commands.RegisterContactUSRequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;



@Service
public class ContactUSCommandService {
	
	
	// creating mongodb template for query
    @Autowired
	MongoTemplate mongoTemplate;
   
	
	/**
     * Service Command method to book a new Cargo
     * @return BookingId of the Cargo
     */

    public void registerContactUSRequest(RegisterContactUSRequestCommand registerContactUSRequestCommand){

    	registerContactUSRequestCommand.setId(RandomStringUtils.random(4, "12345").toUpperCase());
        ContactUS contactus = new ContactUS();
        contactus.setId(registerContactUSRequestCommand.getId());
        contactus.setName(registerContactUSRequestCommand.getName());
        contactus.setEmail(registerContactUSRequestCommand.getEmail());
        contactus.setPhone(registerContactUSRequestCommand.getPhone());
        contactus.setSubject(registerContactUSRequestCommand.getSubject());
        contactus.setUserinputs(registerContactUSRequestCommand.getUserinputs());
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        contactus.setCreatedDate(sd.format(date));
        mongoTemplate.save(contactus);
       
    }

}