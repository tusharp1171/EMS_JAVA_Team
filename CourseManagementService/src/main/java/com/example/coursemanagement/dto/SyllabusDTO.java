package com.example.coursemanagement.dto;

import com.example.coursemanagement.model.Syllabus;

import lombok.Data;

@Data
public class SyllabusDTO {
    private Integer id;
    private String sectionName;
    private String topicName;
    // Include other necessary fields from Syllabus entity

    public SyllabusDTO(Syllabus syllabus) {
        this.id = syllabus.getId();
        this.sectionName = syllabus.getSectionName();
        this.topicName = syllabus.getTopicName();
        // Map other fields as needed
    }
}