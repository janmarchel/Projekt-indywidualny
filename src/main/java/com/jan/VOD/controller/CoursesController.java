package com.jan.VOD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController {
    @GetMapping("/courses")
    public String login(){
        return "courses";
    }
}