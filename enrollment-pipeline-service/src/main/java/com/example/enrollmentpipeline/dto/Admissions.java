package com.example.enrollmentpipeline.dto;

import java.time.LocalDate;

import com.example.enrollmentpipeline.model.Enquiries;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Admissions {

	private int enquiryId;
	private LocalDate admissionDate;
	private String description;
	private String status;

}

