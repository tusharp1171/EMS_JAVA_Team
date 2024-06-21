package com.example.admissionsfee.service;

import java.util.List;

import com.example.admissionsfee.dto.FeePaymentDTO;
import com.example.admissionsfee.entities.FeePayment;

public interface FeePaymentService {
    List<FeePaymentDTO> getAllFeePayments();
    FeePaymentDTO getFeePaymentById(Long id);
    FeePaymentDTO createFeePayment(FeePaymentDTO feePaymentDTO);
    FeePaymentDTO updateFeePayment(Long id, FeePaymentDTO feePaymentDTO);
    void deleteFeePayment(Long id);
    FeePaymentDTO convertToDTO(FeePayment feePayment);
}