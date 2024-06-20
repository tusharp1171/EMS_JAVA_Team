package com.example.facultytimetable.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class TimeTable {

	private int timeTableId;
	private int faultyId;
	private int syllabusId;
	private LocalTime slotStartTime;
	private LocalTime slotEndTime;
	private int weekDayId;
	
}
