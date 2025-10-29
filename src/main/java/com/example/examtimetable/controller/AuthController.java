package com.example.examtimetable.controller;

import com.example.examtimetable.model.User;
import com.example.examtimetable.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Login page
    @GetMapping("/login")
    public String login(@RequestParam(value = "registered", required = false) String registered, Model model) {
        if (registered != null) {
            model.addAttribute("message", "Registration successful. Please login.");
        }
        return "auth/login";
    }

    // Register page (GET)
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    // Register form submission (POST)
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.register(user);
        // Redirect-after-post → prevents duplicate insertion on page refresh
        return "redirect:/login?registered=true";
    }
}
