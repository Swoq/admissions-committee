package com.swoqe.admissionscommittee.security;

import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<ApplicantEntity, UUID> {
    Optional<ApplicantEntity> findBySecurityUserEmail(String email);
    Optional<ApplicantEntity> findBySecurityUser(SecurityUserDetails securityUserDetails);
}
