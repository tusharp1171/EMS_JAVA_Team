package com.example.communicationlog.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.communicationlog.customexception.DataNotFoundException;
import com.example.communicationlog.model.CommunicationLog;
import com.example.communicationlog.repository.CommunicationLogRepository;
import com.example.communicationlog.service.CommunicationService;
import com.example.communicationlog.service.SyallbusMicroserviceApi;
import com.example.communicationlog.service.UserMicroserviceApi;

@Service
public class CommunicationServiceImpl implements CommunicationService {

    @Autowired
    private CommunicationLogRepository communicationLogRepository;

    @Autowired
    private UserMicroserviceApi userApiCall;
    
    @Autowired
    private SyallbusMicroserviceApi syallbusApiCall;
    
    
    
    
    @Override
    public List<CommunicationLog> getAllCommunicationLogs() {
        return communicationLogRepository.findAll();
    }

    @Override
    public CommunicationLog getCommunicationLogById(Integer id) {
        return communicationLogRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Communication log not found with id: " + id));
    }

    @Override
    public CommunicationLog createCommunicationLog(CommunicationLog communicationLog) {
        return communicationLogRepository.save(communicationLog);
    }

    @Override
    public CommunicationLog updateCommunicationLog(Integer id, CommunicationLog communicationLog) {
        CommunicationLog existingCommunicationLog = communicationLogRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Communication log not found with id: " + id));

        existingCommunicationLog.setEnquiryId(communicationLog.getEnquiryId());
        existingCommunicationLog.setCustomerId(communicationLog.getCustomerId());
        existingCommunicationLog.setActvityStatusId(communicationLog.getActvityStatusId());
        existingCommunicationLog.setSalesRepresentativeId(communicationLog.getSalesRepresentativeId());
        existingCommunicationLog.setCommunicationDate(communicationLog.getCommunicationDate());
        existingCommunicationLog.setCommunicationDetails(communicationLog.getCommunicationDetails());

        return communicationLogRepository.save(existingCommunicationLog);
    }

    @Override
    public void deleteCommunicationLog(Integer id) {
        CommunicationLog existingCommunicationLog = communicationLogRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Communication log not found with id: " + id));
        communicationLogRepository.delete(existingCommunicationLog);
    }
}