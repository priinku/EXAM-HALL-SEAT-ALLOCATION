package com.examreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/v1")
public class MyController {

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("index"); // Ensure 'index' is the correct view name for your login page
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("admin");
    }

    @RequestMapping("/faculty")
    public ModelAndView faculty() {
        return new ModelAndView("faculty");
    }

    @RequestMapping("/hall")
    public ModelAndView hall() {
        return new ModelAndView("hall");
    }

    @RequestMapping("/facultydashboard")
    public ModelAndView facultymanagement() {
        return new ModelAndView("facultymanagement");
    }
}
