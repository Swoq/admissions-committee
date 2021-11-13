package com.swoqe.admissionscommittee.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exams_results")
@Data
@ToString(exclude = {"examResults", "applicantEntity"})
public class ExamsResultEntity extends BaseSqlEntity {

    @ElementCollection
    @CollectionTable(name = "applicant_exam",
            joinColumns = {@JoinColumn(name = "result_id", referencedColumnName = "id")})
    @MapKeyEnumerated(value = EnumType.STRING)
    @Column(name = "score")
    private Map<Exams, Float> examResults;

    @OneToOne(mappedBy = "examsResult", optional = false)
    private ApplicantEntity applicantEntity;
}
