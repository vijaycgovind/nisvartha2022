package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EducationDetails {

	private String intendedCourse;
	private String currentYearScore;
	private String currentYearPercentage;
	private String previousYear1Percentage;
	private String previousYear1Score;
	private String previousYear2Percentage;
	private String previousYear2Score;
	private String nameofInstitute;
	private String mediuminInstitute;
	private String principalNameInstitute;
	private String institutePhoneNumber;

}
