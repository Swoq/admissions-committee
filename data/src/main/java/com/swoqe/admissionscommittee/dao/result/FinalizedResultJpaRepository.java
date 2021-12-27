package com.swoqe.admissionscommittee.dao.result;

import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import com.swoqe.admissionscommittee.entity.FinalizedFacultyResultEntity;
import com.swoqe.admissionscommittee.entity.InstitutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FinalizedResultJpaRepository extends JpaRepository<FinalizedFacultyResultEntity, UUID> {
    Page<FinalizedFacultyResultEntity> findAllByFaculty(Pageable pageable, FacultyEntity faculty);

    List<FinalizedFacultyResultEntity> findAllByApplicantAndFinalizedIsTrue(ApplicantEntity user);
}
