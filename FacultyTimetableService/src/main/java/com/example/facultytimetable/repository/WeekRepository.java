package com.example.facultytimetable.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.facultytimetable.model.WeekDays;

@Repository
public interface WeekRepository extends JpaRepository<WeekDays, Integer>{

	Optional<WeekDays> findByWeekDayName(String weekDayName);
}
