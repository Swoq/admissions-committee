package com.swoqe.admissionscommittee.security;

import com.swoqe.admissionscommittee.entity.ApplicantEntity;

import java.util.Optional;

public interface IAuthenticationFacade {
    Optional<ApplicantEntity> getAuthenticationUser();
}