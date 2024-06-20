package com.example.admissionsfee.service;

import java.util.List;

import com.example.admissionsfee.dto.FeePaymentDTO;

public interface FeePaymentService {
    List<FeePaymentDTO> getAllFeePayments();
    FeePaymentDTO getFeePaymentById(Long id);
    FeePaymentDTO createFeePayment(FeePaymentDTO feePaymentDTO);
    FeePaymentDTO updateFeePayment(Long id, FeePaymentDTO feePaymentDTO);
    void deleteFeePayment(Long id);
}