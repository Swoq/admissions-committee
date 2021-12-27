package com.swoqe.admissionscommittee.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class FinalizedFacultyResultEntity extends BaseSqlEntity {

    @OneToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private FacultyEntity faculty;

    @OneToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private ApplicantEntity applicant;

    private BigDecimal meanScore;

    private Boolean finalized = false;

    @Nullable
    private Boolean sponsored = null;

    @Nullable
    private Boolean contract = null;
}
