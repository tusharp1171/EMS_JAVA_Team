package com.example.communicationlog.dto;

import lombok.Data;

@Data
public class EnquiryDto {
	
	 private Integer enquiryId; 
	 private String name;
	 private String email;
	 private String mobileNo;
	 private String enquirySource;
	 private Integer courseId;
	 private Integer pipeLinePhaseId;
	 private Integer salesPersonId;

}
