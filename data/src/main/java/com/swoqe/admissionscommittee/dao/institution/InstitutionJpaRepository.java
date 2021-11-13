package com.swoqe.admissionscommittee.dao.institution;

import com.swoqe.admissionscommittee.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstitutionJpaRepository extends JpaRepository<InstitutionEntity, UUID> {

}
