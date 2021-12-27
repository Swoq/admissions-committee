package com.swoqe.admissionscommittee.controller;

import com.swoqe.admissionscommittee.entity.Subjects;
import com.swoqe.admissionscommittee.service.certificate.CertificateService;
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
@RequestMapping("/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping()
    private String getCertificatePage(Model model) {
        model.addAttribute("subjects", Subjects.values());
        return "create_certificate";
    }

    @PostMapping()
    private String process(@RequestParam("certificate") MultipartFile file,
                           @RequestParam(name = "subjects", required = false) List<Subjects> subjects,
                           @RequestParam(name = "scores", required = false) List<Integer> scores)
            throws IOException {

        certificateService.assignCertificateToCurrentUser(MapUtils.zipToMap(subjects, scores), file.getBytes());
        return "redirect:/account";
    }



}
