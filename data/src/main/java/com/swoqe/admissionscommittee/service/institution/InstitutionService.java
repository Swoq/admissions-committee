package com.swoqe.admissionscommittee.service.institution;

import com.swoqe.admissionscommittee.entity.FacultyEntity;
import com.swoqe.admissionscommittee.entity.InstitutionEntity;
import com.swoqe.admissionscommittee.service.Service;

import java.util.List;

public interface InstitutionService extends Service<InstitutionEntity> {
    List<InstitutionEntity> findAll();
}
