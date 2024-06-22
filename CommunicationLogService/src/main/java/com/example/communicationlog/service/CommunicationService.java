package com.example.communicationlog.service;

import com.example.communicationlog.model.CommunicationLog;
import java.util.List;

public interface CommunicationService {
    List<CommunicationLog> getAllCommunicationLogs();
    CommunicationLog getCommunicationLogById(Integer id);
    CommunicationLog createCommunicationLog(CommunicationLog communicationLog);
    CommunicationLog updateCommunicationLog(Integer id, CommunicationLog communicationLog);
    void deleteCommunicationLog(Integer id);
	CommunicationLog getAllCommunicationData(Integer userId);
}
