package com.example.facultytimetable.service;

import com.example.facultytimetable.dto.SyallbusDto;
import com.example.facultytimetable.model.TimeTable;
import java.util.List;

public interface TimeTableService {
    List<TimeTable> getAllTimeTables();
    TimeTable getTimeTableById(Integer id);
    TimeTable createTimeTable(TimeTable timeTable);
    TimeTable updateTimeTable(Integer id, TimeTable timeTable);
    void deleteTimeTable(Integer id);
	TimeTable createTimeTableWithSyallbus(SyallbusDto syallbusObj);
}
