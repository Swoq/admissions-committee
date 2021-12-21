package com.swoqe.admissionscommittee.service.faculty;

import com.swoqe.admissionscommittee.dao.EntityDao;
import com.swoqe.admissionscommittee.dao.faculty.FacultyDao;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import com.swoqe.admissionscommittee.page.DaoUtil;
import com.swoqe.admissionscommittee.page.PageData;
import com.swoqe.admissionscommittee.page.PageLink;
import com.swoqe.admissionscommittee.service.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FacultyServiceImpl extends AbstractService<FacultyEntity> implements FacultyService {

    private final FacultyDao facultyDao;

    @Override
    protected EntityDao<FacultyEntity> getEntityDao() {
        return facultyDao;
    }

    @Override
    public PageData<FacultyEntity> findAll(PageLink pageLink) {
        Page<FacultyEntity> page = facultyDao.findAll(DaoUtil.toPageable(pageLink, Collections.emptyMap()));
        return new PageData<>(page.getContent(), page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.hasNext());
    }

    @Override
    public Optional<FacultyEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public FacultyEntity save(FacultyEntity entity) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void delete(FacultyEntity entity) {

    }
}
