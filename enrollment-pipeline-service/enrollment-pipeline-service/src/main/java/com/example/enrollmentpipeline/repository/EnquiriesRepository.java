package com.example.enrollmentpipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollmentpipeline.model.Enquiries;
@Repository
public interface EnquiriesRepository extends JpaRepository<Enquiries, Integer> {

}
