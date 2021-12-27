package com.swoqe.admissionscommittee.controller;


import com.swoqe.admissionscommittee.dto.FacultyDto;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import com.swoqe.admissionscommittee.service.faculty.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/{id}")
    private String performSubscription(@PathVariable UUID id) {
        facultyService.enrollCurrentUser(id);
        return "redirect:/account";
    }

    @GetMapping("finalize/{id}")
    private String finaliseFaculty(@PathVariable UUID id) {
        facultyService.finalizeFaculty(id);
        return "redirect:/faculties";
    }

    @GetMapping("/new")
    private String getNewFacultyPage(Model model) {
        model.addAttribute("faculty", new FacultyDto());
        return "new_faculty";
    }

    @GetMapping("/update/{id}")
    private String getUpdatePage(@PathVariable("id") UUID id, Model model) {
        FacultyEntity entity = facultyService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("faculty", facultyService.toFacultyDto(entity));
        return "new_faculty";
    }

    @PostMapping()
    private String saveOrUpdate(@ModelAttribute("faculty") @Valid FacultyDto faculty,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new_faculty";
        facultyService.saveOrUpdateFromDto(faculty);
        return "redirect:/faculties";
    }

    @GetMapping("delete/{id}")
    private String deleteFaculty(@PathVariable UUID id) {
        facultyService.deleteById(id);
        return "redirect:/faculties";
    }
}
