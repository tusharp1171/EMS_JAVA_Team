package com.example.facultytimetable.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class TimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int timeTableId;
	private int faultyId;
	private int syllabusId;
	private LocalTime slotStartTime;
	private LocalTime slotEndTime;
	
	@ManyToOne
    @JoinColumn(name = "weekDayId", nullable = false)
    private WeekDays weekDay;
	
}
