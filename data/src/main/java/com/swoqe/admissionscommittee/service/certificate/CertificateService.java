package com.swoqe.admissionscommittee.service.certificate;

import com.swoqe.admissionscommittee.entity.CertificateEntity;
import com.swoqe.admissionscommittee.entity.Subjects;
import com.swoqe.admissionscommittee.service.Service;

import java.util.Map;

public interface CertificateService extends Service<CertificateEntity> {
    void assignCertificateToCurrentUser(Map<Subjects, Integer> scores, byte[] scanImage);
}
