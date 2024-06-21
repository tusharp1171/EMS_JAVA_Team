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
                .orElseThrow(() -> new ResourceNotFoundException("FeePayment not found with id: " + id));
        return convertToDTO(feePayment);
    }

    @Override
    public FeePaymentDTO createFeePayment(FeePaymentDTO feePaymentDTO) {
        FeePayment feePayment = convertToEntity(feePaymentDTO);
        feePayment = feePaymentRepository.save(feePayment);
        return convertToDTO(feePayment);
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

    @Override
    public FeePaymentDTO convertToDTO(FeePayment feePayment) {
        FeePaymentDTO dto = new FeePaymentDTO();
        dto.setId(feePayment.getId());
        dto.setAmountCredited(feePayment.getAmountCredited());
        dto.setBalanceAmount(feePayment.getBalanceAmount());
        dto.setPaymentDate(feePayment.getPaymentDate());
        dto.setPaymentMethod(feePayment.getPaymentMethod());
        dto.setNextDueDate(feePayment.getNextDueDate());
        dto.setAdmissionId(feePayment.getAdmission().getId());
        return dto;
    }

    private FeePayment convertToEntity(FeePaymentDTO dto) {
        FeePayment feePayment = new FeePayment();
        feePayment.setId(dto.getId());
        feePayment.setAmountCredited(dto.getAmountCredited());
        feePayment.setBalanceAmount(dto.getBalanceAmount());
        feePayment.setPaymentDate(dto.getPaymentDate());
        feePayment.setPaymentMethod(dto.getPaymentMethod());
        feePayment.setNextDueDate(dto.getNextDueDate());

        Admission admission = admissionRepository.findById(dto.getAdmissionId())
                .orElseThrow(() -> new RuntimeException("Admission not found"));
        feePayment.setAdmission(admission);
        return feePayment;
    }
}