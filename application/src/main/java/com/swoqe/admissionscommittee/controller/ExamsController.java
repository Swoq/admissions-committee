package com.swoqe.admissionscommittee.controller;

import com.swoqe.admissionscommittee.entity.Exams;
import com.swoqe.admissionscommittee.entity.Subjects;
import com.swoqe.admissionscommittee.service.certificate.CertificateService;
import com.swoqe.admissionscommittee.service.exams.ExamsService;
import com.swoqe.admissionscommittee.util.MapUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamsController {

    private final ExamsService examsService;

    @GetMapping()
    private String getCertificatePage(Model model) {
        model.addAttribute("subjects", Exams.values());
        return "create_exams";
    }

    @PostMapping()
    private String process(@RequestParam(name = "subjects", required = false) List<Exams> subjects,
                           @RequestParam(name = "scores", required = false) List<Float> scores) {
        examsService.assignExamsToCurrentUser(MapUtils.zipToMap(subjects, scores));
        return "redirect:/account";
    }



}
