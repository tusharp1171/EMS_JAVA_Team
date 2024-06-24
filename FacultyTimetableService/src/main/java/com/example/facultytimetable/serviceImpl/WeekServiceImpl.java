package com.example.facultytimetable.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.facultytimetable.customexception.weekdays.DataNotFoundException;
import com.example.facultytimetable.model.WeekDays;
import com.example.facultytimetable.repository.WeekRepository;
import com.example.facultytimetable.service.WeekService;

@Service
public class WeekServiceImpl implements WeekService {

    @Autowired
    private WeekRepository weekRepository;

    @Override
    public List<WeekDays> getAllWeekDays() {
        return weekRepository.findAll();
    }

    @Override
    public WeekDays getWeekDayById(Integer id) {
        return weekRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("WeekDay not found with id: " + id));
    }

    @Override
    public WeekDays createWeekDay(WeekDays weekDay) {
        return weekRepository.save(weekDay);
    }

    @Override
    public WeekDays updateWeekDay(Integer id, WeekDays weekDay) {
        WeekDays existingWeekDay = weekRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("WeekDay not found with id: " + id));

        existingWeekDay.setWeekDayName(weekDay.getWeekDayName());
        existingWeekDay.setTimeTable(weekDay.getTimeTable());

        return weekRepository.save(existingWeekDay);
    }

    @Override
    public void deleteWeekDay(Integer id) {
        weekRepository.deleteById(id);
    }
}
