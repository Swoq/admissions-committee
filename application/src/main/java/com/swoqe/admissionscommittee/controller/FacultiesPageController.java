package com.swoqe.admissionscommittee.controller;

import com.swoqe.admissionscommittee.page.PageLink;
import com.swoqe.admissionscommittee.service.faculty.FacultyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class FacultiesPageController extends BaseController {

    private FacultyService facultyService;

    @GetMapping("/faculties")
    private String getFacultiesPage(@RequestParam(required = false, defaultValue = "10") int pageSize,
                                    @RequestParam(required = false, defaultValue = "0") int page,
                                    @RequestParam(required = false) String textSearch,
                                    @RequestParam(required = false) String sortProperty,
                                    @RequestParam(required = false) String sortOrder,
                                    Model model) {
        PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);
        model.addAttribute("facultiesPage", facultyService.findAll(pageLink));
        return "faculties";
    }
}
