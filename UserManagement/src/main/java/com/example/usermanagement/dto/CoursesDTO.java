package com.example.usermanagement.dto;

import lombok.Data;

@Data
public class CoursesDTO {
	private Integer id; 

    
    private String courseName;

    
    private String description;

      private double courseFees;
    private int courseDuration;

}
