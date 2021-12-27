package com.swoqe.admissionscommittee.entity;

import com.swoqe.admissionscommittee.security.SecurityUserDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = ModelConstants.APPLICANT_TABLE_NAME)
@Data
@ToString(exclude = {"examsResult", "institution", "certificate", "faculties"})
public class ApplicantEntity extends PersonEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exams_result_id", referencedColumnName = "id")
    private ExamsResultEntity examsResult;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "certificate_id", referencedColumnName = "id")
    private CertificateEntity certificate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "institution_id", referencedColumnName = "id")
    private InstitutionEntity institution;

    @ManyToMany(mappedBy = "applicants")
    private List<FacultyEntity> faculties;

    @OneToOne(cascade = CascadeType.ALL)
    private SecurityUserDetails securityUser;
}
