package com.example.examtimetable.controller;

import com.example.examtimetable.model.Exam;
import com.example.examtimetable.repository.ExamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TimetableController {
    private final ExamRepository examRepo;
    private final com.example.examtimetable.service.TimetableService timetableService;
    public TimetableController(ExamRepository examRepo, com.example.examtimetable.service.TimetableService timetableService){
        this.examRepo = examRepo; this.timetableService = timetableService;
    }

    @GetMapping({"/","/dashboard"})
    public String dashboard(Model m){
        List<Exam> exams = examRepo.findAll();
        m.addAttribute("exams", exams);
        return "dashboard";
    }

    @GetMapping("/timetable/generate")
    public String generatePage(){
        return "timetable/generate";
    }

    @PostMapping("/timetable/generate")
    public String generate(@RequestParam(name = "department", required = false) String department,
                           @RequestParam(name = "from") String from,
                           @RequestParam(name = "to") String to,
                           @RequestParam(name = "maxPerDay", defaultValue = "2") int maxPerDay,
                           @RequestParam(name = "includeWeekends", defaultValue = "false") boolean includeWeekends,
                           RedirectAttributes ra){
        timetableService.generate(department, from, to, maxPerDay, includeWeekends);
        ra.addFlashAttribute("message", "Timetable generated successfully");
        return "redirect:/timetable/generate";
    }

    @GetMapping("/invigilation")
    public String invigilationPage(){
        return "invigilation/index";
    }

    @GetMapping("/reports")
    public String reportsPage(){
        return "reports/index";
    }

    @GetMapping("/rooms")
    public String roomsPage(){
        return "rooms/index";
    }
}
