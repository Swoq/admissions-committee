package com.swoqe.admissionscommittee.service.exams;

import com.swoqe.admissionscommittee.dao.EntityDao;
import com.swoqe.admissionscommittee.dao.applicant.ApplicantDao;
import com.swoqe.admissionscommittee.dao.certificate.CertificateDao;
import com.swoqe.admissionscommittee.entity.CertificateEntity;
import com.swoqe.admissionscommittee.entity.Exams;
import com.swoqe.admissionscommittee.entity.ExamsResultEntity;
import com.swoqe.admissionscommittee.entity.Subjects;
import com.swoqe.admissionscommittee.page.PageData;
import com.swoqe.admissionscommittee.page.PageLink;
import com.swoqe.admissionscommittee.security.IAuthenticationFacade;
import com.swoqe.admissionscommittee.service.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExamsServiceImpl extends AbstractService<ExamsResultEntity> implements ExamsService {

    private final ApplicantDao applicantDao;
    private final IAuthenticationFacade authenticationFacade;


    @Override
    @Transactional
    public void assignExamsToCurrentUser(Map<Exams, Float> scores) {
        authenticationFacade.getAuthenticationUser().ifPresent(user -> {
            ExamsResultEntity examsResult = new ExamsResultEntity();
            examsResult.setId(UUID.randomUUID());
            examsResult.setExamResults(scores);
            examsResult.setApplicantEntity(user);
            user.setExamsResult(examsResult);
            applicantDao.save(user);
        });
    }

    @Override
    protected EntityDao<ExamsResultEntity> getEntityDao() {
        return null;
    }

    @Override
    public PageData<ExamsResultEntity> findAll(PageLink pageLink) {
        return null;
    }

    @Override
    public Optional<ExamsResultEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public ExamsResultEntity save(ExamsResultEntity entity) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void delete(ExamsResultEntity entity) {

    }
}
