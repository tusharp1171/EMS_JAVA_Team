package com.example.facultytimetable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class WeekDays {
	
	private int weekDays;
	private String weekDayName;

}
