package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.application.internal.queryservices;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface NotificationQueryService {
	
	public void sendReminderEmailToCandidatesOnPendingDocuments() throws AddressException, MessagingException, IOException;

}
