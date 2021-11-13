package com.swoqe.admissionscommittee.dao.exams;

import com.swoqe.admissionscommittee.entity.ExamsResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExamsResultJpaRepository extends JpaRepository<ExamsResultEntity, UUID> {

}
