package com.example.admissionsfee.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admissionsfee.dto.AdmissionDTO;
import com.example.admissionsfee.entities.Admission;
import com.example.admissionsfee.exception.ResourceNotFoundException;
import com.example.admissionsfee.repo.AdmissionRepository;
import com.example.admissionsfee.service.AdmissionService;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Override
    public List<AdmissionDTO> getAllAdmissions() {
        return admissionRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AdmissionDTO getAdmissionById(Long id) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + id));
        return convertToDTO(admission);
    }

    @Override
    public AdmissionDTO createAdmission(AdmissionDTO admissionDTO) {
        Admission admission = convertToEntity(admissionDTO);
        Admission savedAdmission = admissionRepository.save(admission);
        return convertToDTO(savedAdmission);
    }

    @Override
    public AdmissionDTO updateAdmission(Long id, AdmissionDTO admissionDTO) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + id));

        admission.setEnquiryId(admissionDTO.getEnquiryId());
        admission.setAdmissionDate(admissionDTO.getAdmissionDate());
        admission.setDescription(admissionDTO.getDescription());
        admission.setStatus(admissionDTO.getStatus());

        Admission updatedAdmission = admissionRepository.save(admission);
        return convertToDTO(updatedAdmission);
    }

    @Override
    public void deleteAdmission(Long id) {
        if (admissionRepository.existsById(id)) {
            admissionRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Admission not found with id: " + id);
        }
    }

    private AdmissionDTO convertToDTO(Admission admission) {
        AdmissionDTO admissionDTO = new AdmissionDTO();
        admissionDTO.setId(admission.getId());
        admissionDTO.setEnquiryId(admission.getEnquiryId());
        admissionDTO.setAdmissionDate(admission.getAdmissionDate());
        admissionDTO.setDescription(admission.getDescription());
        admissionDTO.setStatus(admission.getStatus());
        return admissionDTO;
    }

    private Admission convertToEntity(AdmissionDTO admissionDTO) {
        Admission admission = new Admission();
        admission.setEnquiryId(admissionDTO.getEnquiryId());
        admission.setAdmissionDate(admissionDTO.getAdmissionDate());
        admission.setDescription(admissionDTO.getDescription());
        admission.setStatus(admissionDTO.getStatus());
        return admission;
    }
}