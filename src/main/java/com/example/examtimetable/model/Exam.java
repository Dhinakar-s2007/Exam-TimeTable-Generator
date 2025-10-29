package com.example.examtimetable.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exams")
public class Exam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseCode;
    private String courseName;
    private String department;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String room;
    private String type; // THEORY / PRACTICAL
    // getters/setters omitted for brevity
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getCourseCode(){return courseCode;} public void setCourseCode(String courseCode){this.courseCode=courseCode;}
    public String getCourseName(){return courseName;} public void setCourseName(String courseName){this.courseName=courseName;}
    public String getDepartment(){return department;} public void setDepartment(String department){this.department=department;}
    public LocalDateTime getStartTime(){return startTime;} public void setStartTime(LocalDateTime startTime){this.startTime=startTime;}
    public LocalDateTime getEndTime(){return endTime;} public void setEndTime(LocalDateTime endTime){this.endTime=endTime;}
    public String getRoom(){return room;} public void setRoom(String room){this.room=room;}
    public String getType(){return type;} public void setType(String type){this.type=type;}
}
