package com.example.enrollmentpipeline.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id; 
    @NotNull(message = "courseName is mandatory")
    private String courseName;
    @NotNull(message = "description is mandatory")
    private String description;
    @NotNull(message = "courseFees is mandatory")
    private double courseFees; 
    @NotNull(message = "courseFees is mandatory")
    private int courseDuration; 
//    private int courseTypeId; 
}  
