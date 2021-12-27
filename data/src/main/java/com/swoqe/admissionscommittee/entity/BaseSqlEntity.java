package com.swoqe.admissionscommittee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseSqlEntity {

    @Id
    @Column(name = ModelConstants.ID_PROPERTY_COLUMN, columnDefinition = "uuid")
    protected UUID id;

    @Column(name = ModelConstants.CREATED_TIME_PROPERTY_COLUMN)
    @CreationTimestamp
    protected LocalDateTime createdTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseSqlEntity that = (BaseSqlEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        return result;
    }
}