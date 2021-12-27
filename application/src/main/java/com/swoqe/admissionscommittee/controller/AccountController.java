package com.swoqe.admissionscommittee.controller;

import com.swoqe.admissionscommittee.dao.result.FinalizedResultDao;
import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.FinalizedFacultyResultEntity;
import com.swoqe.admissionscommittee.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AuthService authService;
    private final FinalizedResultDao resultDao;

    @GetMapping()
    private String getAccountPage(Model model) {
        ApplicantEntity user = authService.getCurrentUser()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        List<FinalizedFacultyResultEntity> result = resultDao.findAllByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("result", result);
        return "account";
    }

}
