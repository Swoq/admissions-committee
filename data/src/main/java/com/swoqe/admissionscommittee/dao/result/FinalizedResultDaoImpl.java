package com.swoqe.admissionscommittee.dao.result;

import com.swoqe.admissionscommittee.dao.AbstractDao;
import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import com.swoqe.admissionscommittee.entity.FinalizedFacultyResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinalizedResultDaoImpl extends AbstractDao<FinalizedFacultyResultEntity, FinalizedResultJpaRepository> implements FinalizedResultDao {

    public FinalizedResultDaoImpl(FinalizedResultJpaRepository repository) {
        super(repository);
    }


    @Override
    public Page<FinalizedFacultyResultEntity> findAllByFaculty(Pageable unpaged, FacultyEntity faculty) {
        return repository.findAllByFaculty(unpaged, faculty);
    }

    @Override
    public void saveAll(List<FinalizedFacultyResultEntity> content) {
        repository.saveAll(content);
    }

    @Override
    public List<FinalizedFacultyResultEntity> findAllByUser(ApplicantEntity user) {
        return repository.findAllByApplicantAndFinalizedIsTrue(user);
    }
}
