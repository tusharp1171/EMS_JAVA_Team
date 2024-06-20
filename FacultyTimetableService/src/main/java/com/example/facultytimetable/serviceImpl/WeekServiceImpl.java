package com.example.facultytimetable.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.facultytimetable.customexception.weekdays.InvalidInputException;
import com.example.facultytimetable.model.WeekDays;
import com.example.facultytimetable.repository.WeekRepository;
import com.example.facultytimetable.service.WeekService;

@Service
public class WeekServiceImpl implements WeekService {

	@Autowired
	private WeekRepository weekRepository;

	@Override
	public void findWeekData(String weekDayName) {

		if (weekDayName.isBlank()) {
			throw new InvalidInputException("Given input may contain emptyspace or it is blanck");
		}

		Optional<WeekDays> weekDayData = null;

		try {
			weekDayData = this.weekRepository.findByWeekDayName(weekDayName);
			if (weekDayData.isPresent()) {

			}
		}
		catch (Exception e) {
			e.getMessage();
		}

	}

}
