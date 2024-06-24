package com.example.usermanagement.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class CommunicationLog {
	
	
	private Integer communicationId;
	private Integer enquiryId;
	private Integer customerId;
	private Integer activityId;
	private Integer actvityStatusId;
	private long salesRepresentativeId;
	private LocalDateTime communicationDate;

}
