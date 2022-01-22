package org.nisvarthafoundation.nisvarthaportal.businessservices.NFNotificationBusinessService.application.internal.queryservices;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService{
	
	public void sendReminderEmailToCandidatesOnPendingDocuments() throws AddressException, MessagingException, IOException {
	
		System.out.println("SEND CANDIDATES REMINDER STARTED");
		
	
		   System.out.println("Inside sendmail XXXXXXXXXXXXXXXXXXXXXXXX");
		   Properties props = new Properties();
		   props.put("mail.smtp.starttls.enable", "true");	 
		
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");
		   
		   System.out.println("CONFIG done");   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("vijaycgovind@gmail.com", "salashasuvin@58");
		      }
		   });
		   
		   System.out.println("Auth done >>>>>>>>>>>>>>>>>>>>>>>>>"); 
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("vijaycgovind@gmail.com", false));
		   
		   System.out.println("About to set local time");
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(dtf.format(now));
		   String testmail ="vijaycgovind@gmail.com,champaka.tn@gmail.com";

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vijaycgovind@gmail.com"));
		   System.out.println("completed to list setting");
		   msg.setSubject("Reminder from Nisvartha Foundation:: Please upload report");
		   msg.setContent("Azzzzzzzzzzzzzzzzzzzzzzzzzzzzzz "
		   		+ "funding at " +now , "text/html");
		   msg.setSentDate(new Date());
		   
		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Tutorials point email", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

		   attachPart.attachFile("/var/tmp/image19.png");
		   multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);
		   
		   
		   System.out.println("About send email");
		   Transport.send(msg);   
	}

}
