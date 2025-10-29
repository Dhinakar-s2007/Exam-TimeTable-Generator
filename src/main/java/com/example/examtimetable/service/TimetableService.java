package com.example.examtimetable.service;

import com.example.examtimetable.model.Exam;
import com.example.examtimetable.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TimetableService {
    private final ExamRepository examRepository;

    public TimetableService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    // Basic demo generator: creates sample exams within date range, skips weekends,
    // places two sessions per day (9:00-12:00, 14:00-17:00), simple room assignment.
    public void generate(String department, String from, String to, int maxPerDay, boolean includeWeekends) {
        LocalDate start = LocalDate.parse(from);
        LocalDate end = LocalDate.parse(to);

        // Very basic: clear existing exams for quick re-runs (demo only)
        // In production, use more selective logic.
        examRepository.deleteAll();

        LocalTime[] sessionStarts = new LocalTime[] { LocalTime.of(9,0), LocalTime.of(14,0) };
        LocalTime[] sessionEnds   = new LocalTime[] { LocalTime.of(12,0), LocalTime.of(17,0) };

        int courseIndex = 1;
        for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
            if (!includeWeekends && (d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                continue;
            }
            int sessionsToday = Math.min(maxPerDay, sessionStarts.length);
            for (int i = 0; i < sessionsToday; i++) {
                Exam e = new Exam();
                e.setCourseCode("C" + String.format("%03d", courseIndex));
                e.setCourseName("Course " + courseIndex);
                e.setDepartment(department == null || department.equals("ALL") ? "CSE" : department);
                LocalDateTime st = LocalDateTime.of(d, sessionStarts[i]);
                LocalDateTime en = LocalDateTime.of(d, sessionEnds[i]);
                e.setStartTime(st);
                e.setEndTime(en);
                e.setRoom(i == 0 ? "Room-101" : "Room-202");
                e.setType(courseIndex % 3 == 0 ? "PRACTICAL" : "THEORY");
                examRepository.save(e);
                courseIndex++;
            }
        }
    }
}
