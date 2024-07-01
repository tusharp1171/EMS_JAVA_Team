package com.example.admissionsfee.service;
import java.util.Date;
import java.util.List;

import com.example.admissionsfee.dto.AdmissionDTO;
import com.example.admissionsfee.entities.Admission;

public interface AdmissionService {
    List<AdmissionDTO> getAllAdmissions();
    AdmissionDTO getAdmissionById(Long id);
    Admission createAdmission(Admission admission);
    AdmissionDTO updateAdmission(Long id, AdmissionDTO admissionDTO);
    void deleteAdmission(Long id);
    List<Admission> getAdmissionsByDate(Date date);
    List<Admission> getAdmissionsBetweenDates(Date startDate, Date endDate);
}	