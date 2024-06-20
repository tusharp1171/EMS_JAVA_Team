package com.example.admissionsfee.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionDTO {
	private Long id;
    private Long enquiryId;
    private Date admissionDate;
    private String description;
    private String status;
}