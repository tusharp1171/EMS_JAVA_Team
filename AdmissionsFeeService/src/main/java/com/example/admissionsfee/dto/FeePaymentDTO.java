package com.example.admissionsfee.dto;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeePaymentDTO {
	 private Long id;

	    @Min(value = 0, message = "Amount credited must be zero or more")
	    private double amountCredited;

	    @Min(value = 0, message = "Balance amount must be zero or more")
	    private double balanceAmount;

	    @NotNull(message = "Payment date cannot be null")
	    @FutureOrPresent(message = "Payment date must be in the present or future")
	    private Date paymentDate;

	    @NotBlank(message = "Payment method cannot be blank")
	    private String paymentMethod;

	    @NotNull(message = "Next due date cannot be null")
	    @FutureOrPresent(message = "Next due date must be in the present or future")
	    private Date nextDueDate;

	    @NotNull(message = "Admission ID cannot be null")
	    private Long admissionId;

}
