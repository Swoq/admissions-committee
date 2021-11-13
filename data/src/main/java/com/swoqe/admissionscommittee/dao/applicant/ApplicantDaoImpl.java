package com.swoqe.admissionscommittee.dao.applicant;

import com.swoqe.admissionscommittee.dao.AbstractDao;
import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import org.springframework.stereotype.Component;

@Component
public class ApplicantDaoImpl extends AbstractDao<ApplicantEntity, ApplicantJpaRepository> implements ApplicantDao {

    public ApplicantDaoImpl(ApplicantJpaRepository repository) {
        super(repository);
    }
}
