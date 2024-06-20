package com.example.admissionsfee.service;
import java.util.List;

import com.example.admissionsfee.dto.AdmissionDTO;

public interface AdmissionService {
    List<AdmissionDTO> getAllAdmissions();
    AdmissionDTO getAdmissionById(Long id);
    AdmissionDTO createAdmission(AdmissionDTO admissionDTO);
    AdmissionDTO updateAdmission(Long id, AdmissionDTO admissionDTO);
    void deleteAdmission(Long id);
}	