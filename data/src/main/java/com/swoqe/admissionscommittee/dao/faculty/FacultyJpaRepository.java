package com.swoqe.admissionscommittee.dao.faculty;

import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FacultyJpaRepository extends JpaRepository<FacultyEntity, UUID> {

    @Query("select distinct f from FacultyEntity f " +
            "left join f.applicants a " +
            "where (a is null or a <> :user) " +
            "AND (f.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<FacultyEntity> findAllBySearchTextAndUser(Pageable pageable,
                                                   @Param("user") ApplicantEntity user,
                                                   @Param("name") String textSearch);


}
