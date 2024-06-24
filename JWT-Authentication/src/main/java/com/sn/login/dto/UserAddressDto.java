package com.sn.login.dto;

import lombok.Data;

@Data
public class UserAddressDto {
	
	private int jwtUserId;
	
	private String addressLineOne;
	
	private String addressLineTwo;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String postalCode;

}
