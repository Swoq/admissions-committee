package com.swoqe.admissionscommittee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/")
    private String getMainPage(){
        return "redirect:/faculties";
    }
}
