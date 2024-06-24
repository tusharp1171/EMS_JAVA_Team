package com.example.usermanagement.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Activitydto {

	 
	

		
		private long salesRepresentativeId;
		private LocalDateTime dueDate;
		
		private String summary;		
	    private ActivityType activityType;
	    private ActivityStatus activityStatus;

		

		
	
}
