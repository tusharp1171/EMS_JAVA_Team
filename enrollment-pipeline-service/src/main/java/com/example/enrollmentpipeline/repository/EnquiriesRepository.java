package com.example.enrollmentpipeline.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollmentpipeline.model.Enquiries;
@Repository
public interface EnquiriesRepository extends JpaRepository<Enquiries, Integer> {
    List<Enquiries> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	//List<Enquiries> findAllById(int courseId);

}
