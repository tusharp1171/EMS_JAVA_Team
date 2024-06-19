package com.example.coursemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "course_subject_mapping")
public class CourseSubjectMapping {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "course_type_id")
	    @NotNull(message = "Course type is mandatory")
	    private CourseType courseType;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "subject_id")
	    @NotNull(message = "Subject is mandatory")
	    private Subject subject;
	    
	  
}