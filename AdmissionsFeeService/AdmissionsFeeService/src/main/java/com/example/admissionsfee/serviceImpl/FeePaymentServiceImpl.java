package com.example.admissionsfee.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admissionsfee.dto.FeePaymentDTO;
import com.example.admissionsfee.entities.Admission;
import com.example.admissionsfee.entities.FeePayment;
import com.example.admissionsfee.exception.ResourceNotFoundException;
import com.example.admissionsfee.repo.AdmissionRepository;
import com.example.admissionsfee.repo.FeePaymentRepository;
import com.example.admissionsfee.service.FeePaymentService;

@Service
public class FeePaymentServiceImpl implements FeePaymentService {

    @Autowired
    private FeePaymentRepository feePaymentRepository;

    @Autowired
    private AdmissionRepository admissionRepository;

    @Override
    public List<FeePaymentDTO> getAllFeePayments() {
        return feePaymentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public FeePaymentDTO getFeePaymentById(Long id) {
        FeePayment feePayment = feePaymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee payment not found with id: " + id));
        return convertToDTO(feePayment);
    }

    @Override
    public FeePaymentDTO createFeePayment(FeePaymentDTO feePaymentDTO) {
        FeePayment feePayment = convertToEntity(feePaymentDTO);
        Admission admission = admissionRepository.findById(feePaymentDTO.getAdmissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + feePaymentDTO.getAdmissionId()));
        feePayment.setAdmission(admission);
        FeePayment savedFeePayment = feePaymentRepository.save(feePayment);
        return convertToDTO(savedFeePayment);
    }

    @Override
    public FeePaymentDTO updateFeePayment(Long id, FeePaymentDTO feePaymentDTO) {
        FeePayment feePayment = feePaymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee payment not found with id: " + id));

        feePayment.setAmountCredited(feePaymentDTO.getAmountCredited());
        feePayment.setBalanceAmount(feePaymentDTO.getBalanceAmount());
        feePayment.setPaymentDate(feePaymentDTO.getPaymentDate());
        feePayment.setPaymentMethod(feePaymentDTO.getPaymentMethod());
        feePayment.setNextDueDate(feePaymentDTO.getNextDueDate());

        Admission admission = admissionRepository.findById(feePaymentDTO.getAdmissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + feePaymentDTO.getAdmissionId()));
        feePayment.setAdmission(admission);

        FeePayment updatedFeePayment = feePaymentRepository.save(feePayment);
        return convertToDTO(updatedFeePayment);
    }

    @Override
    public void deleteFeePayment(Long id) {
        if (feePaymentRepository.existsById(id)) {
            feePaymentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Fee payment not found with id: " + id);
        }
    }

    private FeePaymentDTO convertToDTO(FeePayment feePayment) {
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

    private FeePayment convertToEntity(FeePaymentDTO feePaymentDTO) {
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
}