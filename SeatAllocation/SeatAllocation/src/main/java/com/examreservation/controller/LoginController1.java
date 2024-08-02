package com.examreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController1 {

    @GetMapping("/login")
    public String loginPage() {
        return "index"; // Ensure 'login.html' exists in the static folder or templates folder
    }
}
