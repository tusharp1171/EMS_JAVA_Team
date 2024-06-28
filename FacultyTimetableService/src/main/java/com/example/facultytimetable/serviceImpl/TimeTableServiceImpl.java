package com.example.facultytimetable.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.facultytimetable.customexception.weekdays.DataNotFoundException;
import com.example.facultytimetable.dto.SyallbusDto;
import com.example.facultytimetable.model.TimeTable;
import com.example.facultytimetable.model.WeekDays;
import com.example.facultytimetable.repository.TimeTableRepository;
import com.example.facultytimetable.repository.WeekRepository;
import com.example.facultytimetable.service.FaultyDataApi;
import com.example.facultytimetable.service.TimeTableService;

import jakarta.transaction.Transactional;

@Service
public class TimeTableServiceImpl implements TimeTableService {

	@Autowired
	private TimeTableRepository timeTableRepository;

	@Autowired
	private WeekRepository weekRepository;

	@Autowired
	private FaultyDataApi faultyIdApi;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WeekRepository weekDaysRepository;

	@Override
	public List<TimeTable> getAllTimeTables() {
		return this.timeTableRepository.findAll();
	}

	@Override
	public TimeTable getTimeTableById(Integer id) {
		return this.timeTableRepository.findById(id)
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
		return this.timeTableRepository.save(timeTable);
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

		return this.timeTableRepository.save(existingTimeTable);
	}

	@Override
	public void deleteTimeTable(Integer id) {
		this.timeTableRepository.deleteById(id);
	}

	@Transactional
	public TimeTable createTimeTableWithSyallbus(SyallbusDto syallbusObj) {
		// Create a new TimeTable object
		TimeTable saveTimeTable = new TimeTable();

		// Fetch faulty ID from the external API
		ResponseEntity<Integer> faultyIdResponse = this.restTemplate.getForEntity(this.faultyIdApi.getFaultyIdUrl(),
				Integer.class);
		Integer faultyId = faultyIdResponse.getBody();

		// Set properties for the TimeTable object
		saveTimeTable.setFaultyId(faultyId);
		saveTimeTable.setSyllabusId(syallbusObj.getSyallbusId());
		saveTimeTable.setSlotStartTime(LocalDateTime.now());
		saveTimeTable.setSlotEndTime(LocalDateTime.now().plusMonths(6)); // End time is 6 months after the current date

		// Get the weekday for the subject ID
		String weekdayName = getWeekdayNameForSubjectId(syallbusObj.getSubjectId());

		// Find or create the WeekDays object if necessary
		WeekDays weekDay = getOrCreateWeekDay(weekdayName);

		// Set the WeekDays object to the TimeTable
		saveTimeTable.setWeekDay(weekDay);

		// Save the TimeTable object
		return timeTableRepository.save(saveTimeTable);
	}

	private String getWeekdayNameForSubjectId(int subjectId) {
		Map<Integer, String> subjectIdToWeekdayMap = new HashMap<>();
		subjectIdToWeekdayMap.put(1, "Monday");
		subjectIdToWeekdayMap.put(2, "Tuesday");
		subjectIdToWeekdayMap.put(3, "Wednesday");
		subjectIdToWeekdayMap.put(4, "Thursday");
		subjectIdToWeekdayMap.put(5, "Friday");
		subjectIdToWeekdayMap.put(6, "Saturday");
		subjectIdToWeekdayMap.put(7, "Sunday");

		String weekdayName = subjectIdToWeekdayMap.get(subjectId);
		if (weekdayName == null) {
			throw new IllegalArgumentException("Unexpected subject ID: " + subjectId);
		}
		return weekdayName;
	}

	private WeekDays getOrCreateWeekDay(String weekdayName) {
		Optional<WeekDays> weekDayOptional = this.weekDaysRepository.findByWeekDayName(weekdayName);
		if (weekDayOptional.isPresent()) {
			return weekDayOptional.get();
		} else {
			WeekDays newWeekDay = new WeekDays();
			newWeekDay.setWeekDayName(weekdayName);
			return this.weekDaysRepository.save(newWeekDay);
		}
	}
}
