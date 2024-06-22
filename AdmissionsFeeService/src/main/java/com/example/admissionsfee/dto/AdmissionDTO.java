package com.example.admissionsfee.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionDTO {
	 private Long id;

	    @NotNull(message = "Enquiry ID cannot be null")
	    private Long enquiryId;

	    @NotNull(message = "Admission date cannot be null")
	    @FutureOrPresent(message = "Admission date must be in the present or future")
	    private Date admissionDate;

	    @Size(max = 255, message = "Description must be less than 255 characters")
	    private String description;

	    @NotBlank(message = "Status cannot be blank")
	    private String status;

	    @NotNull(message = "Fee payments list cannot be null")
	    private List<FeePaymentDTO> feePayments;
}