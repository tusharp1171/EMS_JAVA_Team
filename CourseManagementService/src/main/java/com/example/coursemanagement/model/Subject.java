	package com.example.coursemanagement.model;
	
	import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
	import jakarta.validation.constraints.NotBlank;
	import lombok.Data;
	
	@Entity
	@Data
	@Table(name = "subjects")
	public class Subject {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	
	    @NotBlank(message = "Subject name is mandatory")
	    private String subjectName;
	
	    private String description;
	    
//	    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	    private Subject subject;
	    
	}