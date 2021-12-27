package com.swoqe.admissionscommittee.util;

import com.swoqe.admissionscommittee.entity.ApplicantEntity;

import java.math.BigDecimal;

public interface ApplicantEvaluationProvider {
    BigDecimal evaluateApplicant(ApplicantEntity applicant);
}
