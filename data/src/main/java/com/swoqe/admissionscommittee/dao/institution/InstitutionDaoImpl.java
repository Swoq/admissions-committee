package com.swoqe.admissionscommittee.dao.institution;

import com.swoqe.admissionscommittee.dao.AbstractDao;
import com.swoqe.admissionscommittee.entity.InstitutionEntity;
import org.springframework.stereotype.Component;

@Component
public class InstitutionDaoImpl extends AbstractDao<InstitutionEntity, InstitutionJpaRepository> implements InstitutionDao {

    public InstitutionDaoImpl(InstitutionJpaRepository repository) {
        super(repository);
    }
}
