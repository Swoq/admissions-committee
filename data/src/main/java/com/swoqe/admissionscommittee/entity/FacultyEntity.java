package com.swoqe.admissionscommittee.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "faculties")
@Data
@ToString(exclude = {"applicants", "finalized"})
public class FacultyEntity extends BaseSqlEntity {

    private String name;
    private Integer sponsoredSlots;
    private Integer availableSlots;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "faculty_applicants",
            joinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    )
    private List<ApplicantEntity> applicants;

    @OneToOne(mappedBy = "faculty", cascade = CascadeType.REMOVE)
    private FinalizedFacultyResultEntity finalized;
}
