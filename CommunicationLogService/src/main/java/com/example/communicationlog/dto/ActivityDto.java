package com.example.communicationlog.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ActivityDto {
	
	private Integer activityId;
	private LocalDateTime dueDate;
	private String summary;
	private ActivityTypeDto activityType;
	private ActivityStatusDto activityStatus;
	
}
