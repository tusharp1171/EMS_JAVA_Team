package com.example.facultytimetable.service;

import com.example.facultytimetable.model.WeekDays;
import java.util.List;

public interface WeekService {
    List<WeekDays> getAllWeekDays();
    WeekDays getWeekDayById(Integer id);
    WeekDays createWeekDay(WeekDays weekDay);
    WeekDays updateWeekDay(Integer id, WeekDays weekDay);
    void deleteWeekDay(Integer id);
}
