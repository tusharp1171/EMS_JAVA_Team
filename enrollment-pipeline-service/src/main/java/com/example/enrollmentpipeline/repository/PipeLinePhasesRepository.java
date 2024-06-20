package com.example.enrollmentpipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollmentpipeline.model.Enquiries;
import com.example.enrollmentpipeline.model.PipeLinePhases;
@Repository
public interface PipeLinePhasesRepository extends JpaRepository<PipeLinePhases, Integer> {

}
