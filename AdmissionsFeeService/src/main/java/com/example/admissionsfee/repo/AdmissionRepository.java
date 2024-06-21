package com.example.admissionsfee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.admissionsfee.entities.Admission;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

}
