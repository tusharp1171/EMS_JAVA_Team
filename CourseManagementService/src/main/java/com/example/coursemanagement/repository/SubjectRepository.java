package com.example.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coursemanagement.model.Subject;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
