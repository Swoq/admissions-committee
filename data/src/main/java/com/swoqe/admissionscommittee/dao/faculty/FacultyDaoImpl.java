package com.swoqe.admissionscommittee.dao.faculty;

import com.swoqe.admissionscommittee.dao.AbstractDao;
import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FacultyDaoImpl extends AbstractDao<FacultyEntity, FacultyJpaRepository> implements FacultyDao {

    public FacultyDaoImpl(FacultyJpaRepository repository) {
        super(repository);
    }

    @Override
    public Page<FacultyEntity> findAllAndNameContains(Pageable pageable, String name, ApplicantEntity applicantEntity) {
        return repository.findAllBySearchTextAndUser(pageable, applicantEntity, name);
    }

    @Override
    public FacultyEntity saveAndFlush(FacultyEntity faculty) {
        return repository.saveAndFlush(faculty);
    }
}
