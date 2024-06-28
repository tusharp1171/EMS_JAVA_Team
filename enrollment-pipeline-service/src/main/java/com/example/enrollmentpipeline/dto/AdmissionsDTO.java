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

public class AdmissionsDTO {

	
	private int enquiryId;
	private LocalDate admissionDate;
	private String description;
	private String status;
	private String  FeePayment;
	public int getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}
	public LocalDate getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFeePayment() {
		return FeePayment;
	}
	public void setFeePaymentDTO(String feePayment) {
		FeePayment = feePayment;
	}

	
}