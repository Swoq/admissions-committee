package com.swoqe.admissionscommittee.service.institution;

import com.swoqe.admissionscommittee.dao.EntityDao;
import com.swoqe.admissionscommittee.dao.faculty.FacultyDao;
import com.swoqe.admissionscommittee.dao.institution.InstitutionDao;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import com.swoqe.admissionscommittee.entity.InstitutionEntity;
import com.swoqe.admissionscommittee.page.DaoUtil;
import com.swoqe.admissionscommittee.page.PageData;
import com.swoqe.admissionscommittee.page.PageLink;
import com.swoqe.admissionscommittee.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl extends AbstractService<InstitutionEntity> implements InstitutionService {

    private final InstitutionDao institutionDao;

    @Override
    protected EntityDao<InstitutionEntity> getEntityDao() {
        return institutionDao;
    }

    @Override
    public PageData<InstitutionEntity> findAll(PageLink pageLink) {
        return null;
    }

    @Override
    public Optional<InstitutionEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public InstitutionEntity save(InstitutionEntity entity) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void delete(InstitutionEntity entity) {

    }

    @Override
    public List<InstitutionEntity> findAll() {
        return institutionDao.findAll(Pageable.unpaged()).getContent();
    }
}
