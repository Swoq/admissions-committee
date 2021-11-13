package com.swoqe.admissionscommittee.dao.applicant;

import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplicantJpaRepository extends JpaRepository<ApplicantEntity, UUID> {

}
