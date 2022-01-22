package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.rules;

public class EventType {
	
	//101 series belongs to student created
	public static final String STUDENTCREATEDEVENTID = "101";
	public static final String STUDENTUPDATEDEVENTID = "102";
	public static final String STUDENTMENTORASSIGN = "103";
	public static final String STUDENTMENTORDEASSIGN = "104";
	public static final String STUDENTDELETE = "105";
	
	public static final String MENTORCREATED_ID = "201";
	public static final String MENTORUPDATED_ID = "202";
	public static final String MENTORDELETED_ID = "203";
	
	public static final String USERCREATED = "301";
	public static final String USERUPDATED = "302";
	public static final String USERDELETED_ID = "303";
	
	

	public static final String CANDIDATEREGISTERED = "Candidate Registered" ;
	public static final String CANDIDATEAPPROVED = "Candidate Approved" ;
	public static final String STUDENTCREATED = "Student Created" ;
	public static final String STUDENTUPDATED = "Student Updated" ;
	public static final String STUDENTDELETED = "Student Deleted" ;
	public static final String FINANCEREPORTING = "Finance reporting" ;
	
	public static final String MENTORDELETED = "Mentor Deleted" ;
	public static final String MENTORUPDATED = "Mentor Updated" ;
	public static final String MENTORCREATED= "Mentor Created" ;
	
	public static final String USERCREDENTIALSUPDATED="Credentails Updated";
	public static final String USERCREDENTIALSCREATED="Credentails Created";
	public static final String USERCREDENTIALSDELETED="Credentails Deleted";
	
	public static final String DOMAIN_STUDENTDOMAIN ="Student";
	public static final String DOMAIN_AUTHORIZATION ="Authorization";
	public static final String DOMAIN_NOTIFICATIAON = "Notification";
	public static final String DOMAIN_REPORING ="Reporting";
	public static final String DOMAIN_ADMINISTRATION="Administration";
	// we have not considered Registration entity for events considering its temp data
	
	
	

}
