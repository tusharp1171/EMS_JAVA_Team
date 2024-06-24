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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCourseFees() {
		return courseFees;
	}
	public void setCourseFees(double courseFees) {
		this.courseFees = courseFees;
	}
	public int getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}
    
}  
