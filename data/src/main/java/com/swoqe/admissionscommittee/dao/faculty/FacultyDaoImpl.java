package com.swoqe.admissionscommittee.dao.faculty;

import com.swoqe.admissionscommittee.dao.AbstractDao;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import org.springframework.stereotype.Component;

@Component
public class FacultyDaoImpl extends AbstractDao<FacultyEntity, FacultyJpaRepository> implements FacultyDao {

    public FacultyDaoImpl(FacultyJpaRepository repository) {
        super(repository);
    }
}
