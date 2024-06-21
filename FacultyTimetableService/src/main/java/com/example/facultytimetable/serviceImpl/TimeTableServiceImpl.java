package com.example.facultytimetable.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.facultytimetable.customexception.weekdays.DataNotFoundException;
import com.example.facultytimetable.model.TimeTable;
import com.example.facultytimetable.model.WeekDays;
import com.example.facultytimetable.repository.TimeTableRepository;
import com.example.facultytimetable.repository.WeekRepository;
import com.example.facultytimetable.service.TimeTableService;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private WeekRepository weekRepository;

    @Override
    public List<TimeTable> getAllTimeTables() {
        return timeTableRepository.findAll();
    }

    @Override
    public TimeTable getTimeTableById(Integer id) {
        return timeTableRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TimeTable not found with id: " + id));
    }

    @Override
    public TimeTable createTimeTable(TimeTable timeTable) {
        WeekDays weekDays = timeTable.getWeekDay();
        if (weekDays != null) {
            weekDays.setTimeTable(timeTable); // Set bidirectional relationship
            weekDays = weekRepository.save(weekDays); // Save WeekDays entity
            timeTable.setWeekDay(weekDays); // Ensure TimeTable entity references the saved WeekDays
        }
        return timeTableRepository.save(timeTable);
    }

    @Override
    public TimeTable updateTimeTable(Integer id, TimeTable timeTable) {
        TimeTable existingTimeTable = timeTableRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("TimeTable not found with id: " + id));

        existingTimeTable.setFaultyId(timeTable.getFaultyId());
        existingTimeTable.setSyllabusId(timeTable.getSyllabusId());
        existingTimeTable.setSlotStartTime(timeTable.getSlotStartTime());
        existingTimeTable.setSlotEndTime(timeTable.getSlotEndTime());

        WeekDays weekDays = timeTable.getWeekDay();
        if (weekDays != null) {
            weekDays.setTimeTable(existingTimeTable); // Set bidirectional relationship
            weekDays = weekRepository.save(weekDays); // Save WeekDays entity
            existingTimeTable.setWeekDay(weekDays); // Ensure TimeTable entity references the saved WeekDays
        }

        return timeTableRepository.save(existingTimeTable);
    }

    @Override
    public void deleteTimeTable(Integer id) {
        timeTableRepository.deleteById(id);
    }
}
