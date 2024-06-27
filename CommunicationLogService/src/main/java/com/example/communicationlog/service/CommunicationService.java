package com.example.communicationlog.service;

import com.example.communicationlog.dto.ActivityDto;
import com.example.communicationlog.dto.EnquiryDto;
import com.example.communicationlog.model.CommunicationLog;
import java.util.List;
import java.util.Optional;

public interface CommunicationService {
    List<CommunicationLog> getAllCommunicationLogs();
    CommunicationLog getCommunicationLogById(Integer id);
    CommunicationLog createCommunicationLog(CommunicationLog communicationLog);
    CommunicationLog updateCommunicationLog(Integer id, CommunicationLog communicationLog);
    void deleteCommunicationLog(Integer id);
	CommunicationLog getAllCommunicationData(Integer userId);
	CommunicationLog createCommLogWithEnquiry(EnquiryDto enqObj);
	CommunicationLog createCommunicationLogWithActivity(ActivityDto actObj);
	Optional<CommunicationLog> findCommunicationDataByUserId(long salesRepresentativeId);
	List<CommunicationLog> findCommunicationLogsBySalesRepresentativeId();
	Integer findEnquiryId();
}
