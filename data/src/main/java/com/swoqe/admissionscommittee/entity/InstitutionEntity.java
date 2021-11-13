package com.swoqe.admissionscommittee.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "institutions")
@Data
@ToString(exclude = {"applicants"})
public class InstitutionEntity extends BaseSqlEntity {

    private String name;
    private String city;
    private String address;

    @OneToMany(mappedBy = "institution")
    private List<ApplicantEntity> applicants;
}
