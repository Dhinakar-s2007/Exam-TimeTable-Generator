package com.example.examtimetable.repository;

import com.example.examtimetable.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> { }
