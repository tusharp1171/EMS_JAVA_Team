package com.example.usermanagement.dto;

import lombok.Data;

@Data
public class EnquiryDto {
    private String name;
    private String email;
    private String mobileNo;
    private String enquirySource;
    private CoursesDTO courses;
    private PipeLinePhasesDTO pipeLinePhases;
    private long salesPersonId;
}