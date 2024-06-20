package com.example.facultytimetable.model;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class WeekDays {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int weekDayId;
	
	private String weekDayName;
	
	@OneToMany(mappedBy = "timeTableId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TimeTable> timeTables;

}
