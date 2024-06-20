package com.example.admissionsfee.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeePaymentDTO {
	 private Long id;
	    private Long admissionId;
	    private double amountCredited;
	    private double balanceAmount;
	    private Date paymentDate;
	    private String paymentMethod;
	    private Date nextDueDate;
    
}
