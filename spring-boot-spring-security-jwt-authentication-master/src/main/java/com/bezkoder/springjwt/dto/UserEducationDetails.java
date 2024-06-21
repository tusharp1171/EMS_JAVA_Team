package com.bezkoder.springjwt.dto;


import lombok.Data;

@Data
public class UserEducationDetails {

	private long userId;
	
	private String educationTitle;
	private String description;
	private Integer passingYear;


}
