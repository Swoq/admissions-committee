package com.swoqe.admissionscommittee.dao.certificate;

import com.swoqe.admissionscommittee.dao.AbstractDao;
import com.swoqe.admissionscommittee.entity.CertificateEntity;
import org.springframework.stereotype.Component;

@Component
public class CertificateDaoImpl extends AbstractDao<CertificateEntity, CertificateJpaRepository> implements CertificateDao {

    public CertificateDaoImpl(CertificateJpaRepository repository) {
        super(repository);
    }
}
