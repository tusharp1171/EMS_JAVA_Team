package com.example.admissionsfee.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admissionsfee.dto.AdmissionDTO;
import com.example.admissionsfee.entities.Admission;
import com.example.admissionsfee.exception.ResourceNotFoundException;
import com.example.admissionsfee.repo.AdmissionRepository;
import com.example.admissionsfee.repo.FeePaymentRepository;
import com.example.admissionsfee.service.AdmissionService;

import com.example.admissionsfee.dto.FeePaymentDTO;
import com.example.admissionsfee.entities.FeePayment;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;
    
    @Autowired
    private FeePaymentRepository feePaymentRepository;

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

//    @Override
//    public AdmissionDTO createAdmission(AdmissionDTO admissionDTO) {
//        Admission admission = convertToEntity(admissionDTO);
//       
//        admission.setAdmissionDate(new Date());
//
//        // Save admission first to generate the ID
//        System.out.println(admission.getAdmissionDate());
//        Admission savedAdmission = admissionRepository.save(admission);
//
//        // Now create and save FeePayments if they exist
//        List<FeePaymentDTO> feesdto = admissionDTO.getFeePayments();
//        if (feesdto != null) {
//            for (FeePaymentDTO feeDTO : feesdto) {
//                FeePayment feePayment = new FeePayment();
//                feePayment.setAmountCredited(feeDTO.getAmountCredited());
//                feePayment.setBalanceAmount(feeDTO.getBalanceAmount());
//                feePayment.setPaymentDate(feeDTO.getPaymentDate());
//                feePayment.setPaymentMethod(feeDTO.getPaymentMethod());
//                feePayment.setNextDueDate(feeDTO.getNextDueDate());
//
//                // Set the saved Admission to the FeePayment
//                feePayment.setAdmission(savedAdmission);
//
//                // Save the FeePayment
//                feePaymentRepository.save(feePayment);
//            }
//        }
//
//        return convertToDTO(savedAdmission);
//    }
    
    
    @Override
    public Admission createAdmission(Admission admission) {
        // Set the current date as the admission date
        admission.setAdmissionDate(new Date());

        // Save admission first to generate the ID
        System.out.println(admission.getAdmissionDate());
        Admission savedAdmission = admissionRepository.save(admission);

        // Now create and save FeePayments if they exist
        List<FeePayment> feePayments = admission.getFeePayments();
        if (feePayments != null) {
            for (FeePayment feePayment : feePayments) {
                // Set the saved Admission to the FeePayment
                feePayment.setAdmission(savedAdmission);

                // Save the FeePayment
                feePaymentRepository.save(feePayment);
            }
        }

        return savedAdmission;
    }

    @Override
    public AdmissionDTO updateAdmission(Long id, AdmissionDTO admissionDTO) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + id));

        admission.setEnquiryId(admissionDTO.getEnquiryId());
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
        admissionDTO.setDescription(admission.getDescription());
        admissionDTO.setStatus(admission.getStatus());
        // Convert FeePayments to FeePaymentDTOs
        if (admission.getFeePayments() != null) {
            List<FeePaymentDTO> feePaymentDTOs = admission.getFeePayments().stream()
                .map(this::convertFeePaymentToDTO)
                .collect(Collectors.toList());
            admissionDTO.setFeePayments(feePaymentDTOs);
        }
        return admissionDTO;
    }

    private Admission convertToEntity(AdmissionDTO admissionDTO) {
        Admission admission = new Admission();
        admission.setEnquiryId(admissionDTO.getEnquiryId());
        admission.setDescription(admissionDTO.getDescription());
        admission.setStatus(admissionDTO.getStatus());
        // Convert FeePaymentDTOs to FeePayments
        if (admissionDTO.getFeePayments() != null) {
            List<FeePayment> feePayments = admissionDTO.getFeePayments().stream()
                .map(this::convertFeePaymentToEntity)
                .collect(Collectors.toList());
            admission.setFeePayments(feePayments);
        }
        return admission;
    }

    private FeePaymentDTO convertFeePaymentToDTO(FeePayment feePayment) {
        FeePaymentDTO feePaymentDTO = new FeePaymentDTO();
        feePaymentDTO.setId(feePayment.getId());
        feePaymentDTO.setAdmissionId(feePayment.getAdmission() != null ? feePayment.getAdmission().getId() : null);
        feePaymentDTO.setAmountCredited(feePayment.getAmountCredited());
        feePaymentDTO.setBalanceAmount(feePayment.getBalanceAmount());
        feePaymentDTO.setPaymentDate(feePayment.getPaymentDate());
        feePaymentDTO.setPaymentMethod(feePayment.getPaymentMethod());
        feePaymentDTO.setNextDueDate(feePayment.getNextDueDate());
        return feePaymentDTO;
    }

    private FeePayment convertFeePaymentToEntity(FeePaymentDTO feePaymentDTO) {
        FeePayment feePayment = new FeePayment();
        feePayment.setAmountCredited(feePaymentDTO.getAmountCredited());
        feePayment.setBalanceAmount(feePaymentDTO.getBalanceAmount());
        feePayment.setPaymentDate(feePaymentDTO.getPaymentDate());
        feePayment.setPaymentMethod(feePaymentDTO.getPaymentMethod());
        feePayment.setNextDueDate(feePaymentDTO.getNextDueDate());
        Admission admission = admissionRepository.findById(feePaymentDTO.getAdmissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + feePaymentDTO.getAdmissionId()));
        feePayment.setAdmission(admission);
        return feePayment;
    }
    
    @Override
    public List<Admission> getAdmissionsByDate(Date date) {
        return admissionRepository.findAdmissionsByDate(date);
    }
    @Override
    public List<Admission> getAdmissionsBetweenDates(Date startDate, Date endDate) {
        return admissionRepository.findAdmissionsBetweenDates(startDate, endDate);
    }
}