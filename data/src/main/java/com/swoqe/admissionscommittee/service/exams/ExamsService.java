package com.swoqe.admissionscommittee.service.exams;

import com.swoqe.admissionscommittee.entity.CertificateEntity;
import com.swoqe.admissionscommittee.entity.Exams;
import com.swoqe.admissionscommittee.entity.ExamsResultEntity;
import com.swoqe.admissionscommittee.entity.Subjects;
import com.swoqe.admissionscommittee.service.Service;

import java.util.Map;

public interface ExamsService extends Service<ExamsResultEntity> {
    void assignExamsToCurrentUser(Map<Exams, Float> scores);
}
