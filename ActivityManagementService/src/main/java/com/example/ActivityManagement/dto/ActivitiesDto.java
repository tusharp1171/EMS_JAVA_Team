package com.example.ActivityManagement.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ActivitiesDto {

	
	private Integer id;
    private Integer activityStatusId;
    private Integer salesRepresentativeId;

	
	
	
}



