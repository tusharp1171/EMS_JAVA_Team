package com.sn.login.dto;

import lombok.Data;

@Data
public class UserEducationDetails {

	private int jwtUserId;
	
	private String educationTitle;
	private String description;
	private Integer passingYear;


}
