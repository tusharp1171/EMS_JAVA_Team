package com.example.facultytimetable.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "time_table")
public class TimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer timeTableId;
	private Integer faultyId;
	private Integer syllabusId;
	private LocalDateTime slotStartTime;
	private LocalDateTime slotEndTime;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "week_day_id", referencedColumnName = "weekDayId")
	private WeekDays weekDay;
}
