package com.swoqe.admissionscommittee.dao.faculty;

import com.swoqe.admissionscommittee.dao.EntityDao;
import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacultyDao extends EntityDao<FacultyEntity> {
    Page<FacultyEntity> findAllAndNameContains(Pageable pageable, String name, ApplicantEntity applicantEntity);
    FacultyEntity saveAndFlush(FacultyEntity faculty);
}
