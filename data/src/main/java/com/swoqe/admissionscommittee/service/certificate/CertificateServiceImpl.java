package com.swoqe.admissionscommittee.service.certificate;

import com.swoqe.admissionscommittee.dao.EntityDao;
import com.swoqe.admissionscommittee.dao.applicant.ApplicantDao;
import com.swoqe.admissionscommittee.dao.certificate.CertificateDao;
import com.swoqe.admissionscommittee.dto.ApplicantDto;
import com.swoqe.admissionscommittee.entity.CertificateEntity;
import com.swoqe.admissionscommittee.entity.Subjects;
import com.swoqe.admissionscommittee.page.DaoUtil;
import com.swoqe.admissionscommittee.page.PageData;
import com.swoqe.admissionscommittee.page.PageLink;
import com.swoqe.admissionscommittee.security.IAuthenticationFacade;
import com.swoqe.admissionscommittee.service.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CertificateServiceImpl extends AbstractService<CertificateEntity> implements CertificateService {

    private final CertificateDao certificateDao;
    private final ApplicantDao applicantDao;
    private final IAuthenticationFacade authenticationFacade;

    @Override
    protected EntityDao<CertificateEntity> getEntityDao() {
        return certificateDao;
    }

    @Override
    public PageData<CertificateEntity> findAll(PageLink pageLink) {
        Page<CertificateEntity> page = certificateDao.findAll(DaoUtil.toPageable(pageLink, Collections.emptyMap()));
        return new PageData<>(page.getContent(), page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.hasNext());
    }

    @Override
    public Optional<CertificateEntity> findById(UUID id) {
        return certificateDao.findById(id);
    }

    @Override
    public CertificateEntity save(CertificateEntity entity) {
        return certificateDao.save(entity);
    }

    @Override
    public void deleteById(UUID id) {
        certificateDao.deleteById(id);
    }

    @Override
    public void delete(CertificateEntity entity) {
        certificateDao.delete(entity);
    }

    @Override
    @Transactional
    public void assignCertificateToCurrentUser(Map<Subjects, Integer> scores, byte[] scanImage) {
        authenticationFacade.getAuthenticationUser().ifPresent(user -> {
            CertificateEntity certificateEntity = new CertificateEntity();
            certificateEntity.setId(UUID.randomUUID());
            certificateEntity.setExamResults(scores);
            certificateEntity.setScan(scanImage);
            certificateEntity.setApplicant(user);
            user.setCertificate(certificateEntity);
            applicantDao.save(user);
        });
    }
}
