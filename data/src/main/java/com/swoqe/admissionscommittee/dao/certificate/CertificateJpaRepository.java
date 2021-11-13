package com.swoqe.admissionscommittee.dao.certificate;

import com.swoqe.admissionscommittee.entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificateJpaRepository extends JpaRepository<CertificateEntity, UUID> {

}
