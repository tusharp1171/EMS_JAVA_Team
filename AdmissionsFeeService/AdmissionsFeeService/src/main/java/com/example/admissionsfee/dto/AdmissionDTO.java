package com.example.admissionsfee.dto;

import java.util.Date;
import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionDTO {
	private Long id;
	
	private int courseTypeId;
    private int subjectId;
    private long userid;
	
    private int enquiryId;
    private String description;
    private String status;
    
    
    private List<FeePaymentDTO> feePayments;
}