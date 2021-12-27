package com.swoqe.admissionscommittee.service.certificate;

import com.swoqe.admissionscommittee.dao.applicant.ApplicantDao;
import com.swoqe.admissionscommittee.dao.certificate.CertificateDao;
import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.CertificateEntity;
import com.swoqe.admissionscommittee.security.IAuthenticationFacade;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CertificateServiceImpl.class})
class CertificateServiceImplTest {

    @Autowired
    CertificateServiceImpl certificateService;

    @MockBean
    CertificateDao certificateDao;

    @MockBean
    ApplicantDao applicantDao;

    @MockBean
    IAuthenticationFacade authenticationFacade;

    @Captor
    ArgumentCaptor<ApplicantEntity> applicantCaptor;

    @Test
    void assignCertificateToCurrentUser() {
        ApplicantEntity applicant = new ApplicantEntity();
        applicant.setId(UUID.randomUUID());
        Mockito.when(authenticationFacade.getAuthenticationUser()).thenReturn(Optional.of(applicant));

        certificateService.assignCertificateToCurrentUser(Collections.emptyMap(), new byte[0]);

        Mockito.verify(applicantDao, Mockito.times(1)).save(applicantCaptor.capture());
        ApplicantEntity captured = applicantCaptor.getValue();

        assertThat(captured.getId()).isEqualTo(applicant.getId());
        assertThat(captured.getCertificate()).isNotNull();
        assertThat(captured.getCertificate().getApplicant().getId()).isEqualTo(applicant.getId());

    }
}