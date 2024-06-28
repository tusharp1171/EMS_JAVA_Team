package com.example.admissionsfee.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.admissionsfee.entities.Admission;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

	List<Admission> findByAdmissionDate(Date admissionDate);

    List<Admission> findByAdmissionDateBetween(Date startOfDay, Date endOfDay);
}
