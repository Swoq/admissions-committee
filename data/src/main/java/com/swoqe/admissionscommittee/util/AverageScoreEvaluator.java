package com.swoqe.admissionscommittee.util;

import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.Exams;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.Map;

@Component
@ConditionalOnProperty(value = "evaluation.strategy", havingValue = "averageScore", matchIfMissing = true)
public class AverageScoreEvaluator implements ApplicantEvaluationProvider {

    @Override
    public BigDecimal evaluateApplicant(ApplicantEntity applicant) {
        Map<Exams, Float> examResults = applicant.getExamsResult().getExamResults();
        DoubleSummaryStatistics doubleSummaryStatistics = examResults.values().stream()
                .mapToDouble(x -> x).summaryStatistics();
        return BigDecimal.valueOf(doubleSummaryStatistics.getAverage());
    }
}
