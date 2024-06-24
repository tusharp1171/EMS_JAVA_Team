package com.bezkoder.springjwt.dto;

import lombok.Data;

@Data
public class UserAddressDto {
	
	private long userId;
	
	private String addressLineOne;
	
	private String addressLineTwo;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String postalCode;

}
