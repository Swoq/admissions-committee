package com.swoqe.admissionscommittee.controller;

import com.swoqe.admissionscommittee.dao.applicant.ApplicantDao;
import com.swoqe.admissionscommittee.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/applicant")
@RequiredArgsConstructor
public class ApplicantController {

    private final AuthService authService;

    @PostMapping("/block")
    private String blockApplicant(@RequestParam("email") String email) {
        authService.blockUserByEmail(email);
        return "redirect:/faculties";
    }

    @PostMapping("/unblock")
    private String unblockApplicant(@RequestParam("email") String email) {
        authService.unblockUserByEmail(email);
        return "redirect:/faculties";
    }
}
