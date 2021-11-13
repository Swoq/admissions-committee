package com.swoqe.admissionscommittee.dao.faculty;

import com.swoqe.admissionscommittee.entity.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FacultyJpaRepository extends JpaRepository<FacultyEntity, UUID> {

}
