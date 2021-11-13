package com.swoqe.admissionscommittee.dao.exams;

import com.swoqe.admissionscommittee.dao.AbstractDao;
import com.swoqe.admissionscommittee.entity.ExamsResultEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamsResultDaoImpl extends AbstractDao<ExamsResultEntity, ExamsResultJpaRepository> implements ExamsResultDao {

    public ExamsResultDaoImpl(ExamsResultJpaRepository repository) {
        super(repository);
    }
}
