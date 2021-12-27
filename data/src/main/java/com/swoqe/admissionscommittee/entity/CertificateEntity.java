package com.swoqe.admissionscommittee.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "certificates")
@Data
@ToString(exclude = {"examResults", "applicant"})
public class CertificateEntity extends BaseSqlEntity {

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] scan;

    @ElementCollection
    @CollectionTable(name = "applicant_certificate_subject",
            joinColumns = {@JoinColumn(name = "certificate_id", referencedColumnName = "id")})
    @MapKeyEnumerated(value = EnumType.STRING)
    @Column(name = "score")
    private Map<Subjects, Integer> examResults;

    @OneToOne(mappedBy = "certificate")
    private ApplicantEntity applicant;
}
