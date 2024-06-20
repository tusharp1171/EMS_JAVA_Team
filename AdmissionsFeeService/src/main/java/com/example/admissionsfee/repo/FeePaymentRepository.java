package com.example.admissionsfee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.admissionsfee.entities.FeePayment;

public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {

}
