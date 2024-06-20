package com.example.coursemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "syllabus")
public class Syllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    @NotNull(message = "Subject is mandatory")
    private Subject subject;

    @NotBlank(message = "Section name is mandatory")
    private String sectionName;

    @NotBlank(message = "Topic name is mandatory")
    private String topicName;

    @ManyToOne
    @JoinColumn(name = "courseTypeId")
    @NotNull(message = "Course type is mandatory")
    private CourseType courseType;

}