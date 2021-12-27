package com.swoqe.admissionscommittee.security;


import com.swoqe.admissionscommittee.entity.ApplicantEntity;

import java.util.Optional;

public interface AuthService {

    void signUpUser(RegistrationRequest request);

    void blockUserByEmail(String email);

    void unblockUserByEmail(String email);

    Optional<ApplicantEntity> getCurrentUser();

}
