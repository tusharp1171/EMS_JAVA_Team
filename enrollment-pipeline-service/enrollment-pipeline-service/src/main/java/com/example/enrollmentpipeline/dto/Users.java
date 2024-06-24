package com.example.enrollmentpipeline.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Users {

	 private String firstName;
	 private String lastName ;
	 private LocalDate DOB;
	 private int userTypeId;
}
