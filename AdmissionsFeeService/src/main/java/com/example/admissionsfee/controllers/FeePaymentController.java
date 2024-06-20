package com.example.admissionsfee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.admissionsfee.dto.FeePaymentDTO;
import com.example.admissionsfee.service.FeePaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/feepayments")
@Tag(name = "Fee Payments", description = "Fee Payments management APIs")
public class FeePaymentController {

    @Autowired
    private FeePaymentService feePaymentService;

    @GetMapping
    @Operation(summary = "Get all fee payments", description = "Retrieve a list of all fee payments")
    public List<FeePaymentDTO> getAllFeePayments() {
        return feePaymentService.getAllFeePayments();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get fee payment by ID", description = "Retrieve a single fee payment by its ID")
    public ResponseEntity<FeePaymentDTO> getFeePaymentById(@PathVariable Long id) {
        FeePaymentDTO feePaymentDTO = feePaymentService.getFeePaymentById(id);
        return ResponseEntity.ok(feePaymentDTO);
    }

    @PostMapping
    @Operation(summary = "Create a new fee payment", description = "Create a new fee payment")
    public ResponseEntity<FeePaymentDTO> createFeePayment(@RequestBody FeePaymentDTO feePaymentDTO) {
        FeePaymentDTO createdFeePayment = feePaymentService.createFeePayment(feePaymentDTO);
        return ResponseEntity.ok(createdFeePayment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing fee payment", description = "Update an existing fee payment by its ID")
    public ResponseEntity<FeePaymentDTO> updateFeePayment(@PathVariable Long id, @RequestBody FeePaymentDTO feePaymentDTO) {
        FeePaymentDTO updatedFeePayment = feePaymentService.updateFeePayment(id, feePaymentDTO);
        return ResponseEntity.ok(updatedFeePayment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a fee payment", description = "Delete a fee payment by its ID")
    public ResponseEntity<Void> deleteFeePayment(@PathVariable Long id) {
        feePaymentService.deleteFeePayment(id);
        return ResponseEntity.noContent().build();
    }
}