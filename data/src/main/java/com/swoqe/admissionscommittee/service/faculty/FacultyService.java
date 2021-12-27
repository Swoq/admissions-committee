package com.swoqe.admissionscommittee.service.faculty;

import com.swoqe.admissionscommittee.dto.FacultyDto;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import com.swoqe.admissionscommittee.service.Service;

import java.util.UUID;

public interface FacultyService extends Service<FacultyEntity> {
    void enrollCurrentUser(UUID facultyId);

    void finalizeFaculty(UUID id);

    void saveOrUpdateFromDto(FacultyDto facultyDto);

    FacultyDto toFacultyDto(FacultyEntity facultyEntity);

    FacultyEntity toFacultyEntity(FacultyDto facultyDto);
}
