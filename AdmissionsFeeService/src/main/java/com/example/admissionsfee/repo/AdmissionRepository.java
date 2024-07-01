package com.example.admissionsfee.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.admissionsfee.entities.Admission;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

	List<Admission> findByAdmissionDate(Date admissionDate);

    List<Admission> findByAdmissionDateBetween(Date startOfDay, Date endOfDay);
    
    
    @Query("SELECT a FROM Admission a WHERE DATE(a.admissionDate) = DATE(:currentDate)")
    List<Admission> findAdmissionsByDate(@Param("currentDate") Date currentDate);
    
    @Query("SELECT a FROM Admission a WHERE a.admissionDate BETWEEN :startDate AND :endDate")
    List<Admission> findAdmissionsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
