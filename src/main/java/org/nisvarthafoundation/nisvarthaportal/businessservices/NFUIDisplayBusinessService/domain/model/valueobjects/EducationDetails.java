package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.valueobjects;

import lombok.Data;
@Data
public class EducationDetails {

	private SchoolDetails schoolDetails;
	private CollegeDetails collegeDetails;
	private String currentCourse;
	private String currentStatus;

}
