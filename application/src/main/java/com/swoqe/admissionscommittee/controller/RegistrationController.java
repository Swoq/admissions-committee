package com.swoqe.admissionscommittee.controller;

import com.swoqe.admissionscommittee.page.PageLink;
import com.swoqe.admissionscommittee.security.AuthService;
import com.swoqe.admissionscommittee.security.RegistrationRequest;
import com.swoqe.admissionscommittee.service.institution.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final AuthService authService;
    private final InstitutionService institutionService;

    @GetMapping("/registration")
    private String getRegistrationPage(Model model) {
        model.addAttribute("request", new RegistrationRequest());
        model.addAttribute("institutions", institutionService.findAll());
        return "registration";
    }

    @PostMapping("/registration")
    private String performRegistration(@ModelAttribute("request") @Valid RegistrationRequest request,
                                       BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())
            return "registration";

        if(!request.getPassword().equals(request.getMatchingPassword())) {
            model.addAttribute("matchError", "Passwords should be the same!");
            model.addAttribute("institutions", institutionService.findAll());
            return "registration";
        }
        authService.signUpUser(request);
        return "redirect:/login";
    }
}
