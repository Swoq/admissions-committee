package com.swoqe.admissionscommittee.dao.result;

import com.swoqe.admissionscommittee.dao.EntityDao;
import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import com.swoqe.admissionscommittee.entity.FinalizedFacultyResultEntity;
import com.swoqe.admissionscommittee.entity.InstitutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinalizedResultDao extends EntityDao<FinalizedFacultyResultEntity> {
    Page<FinalizedFacultyResultEntity> findAllByFaculty(Pageable unpaged, FacultyEntity faculty);

    void saveAll(List<FinalizedFacultyResultEntity> content);

    List<FinalizedFacultyResultEntity> findAllByUser(ApplicantEntity user);
}
