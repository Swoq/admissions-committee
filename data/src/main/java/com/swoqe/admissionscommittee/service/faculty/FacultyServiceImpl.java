package com.swoqe.admissionscommittee.service.faculty;

import com.swoqe.admissionscommittee.dao.EntityDao;
import com.swoqe.admissionscommittee.dao.applicant.ApplicantDao;
import com.swoqe.admissionscommittee.dao.faculty.FacultyDao;
import com.swoqe.admissionscommittee.dao.result.FinalizedResultDao;
import com.swoqe.admissionscommittee.dto.FacultyDto;
import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.FacultyEntity;
import com.swoqe.admissionscommittee.entity.FinalizedFacultyResultEntity;
import com.swoqe.admissionscommittee.page.DaoUtil;
import com.swoqe.admissionscommittee.page.PageData;
import com.swoqe.admissionscommittee.page.PageLink;
import com.swoqe.admissionscommittee.security.IAuthenticationFacade;
import com.swoqe.admissionscommittee.service.AbstractService;
import com.swoqe.admissionscommittee.util.ApplicantEvaluationProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl extends AbstractService<FacultyEntity> implements FacultyService {

    private final FacultyDao facultyDao;
    private final FinalizedResultDao finalizedResultDao;
    private final ApplicantEvaluationProvider evaluationProvider;
    private final IAuthenticationFacade authenticationFacade;

    public FacultyServiceImpl(FacultyDao facultyDao, ApplicantDao applicantDao, FinalizedResultDao finalizedResultDao, ApplicantEvaluationProvider evaluationProvider, IAuthenticationFacade authenticationFacade) {
        this.facultyDao = facultyDao;
        this.finalizedResultDao = finalizedResultDao;
        this.evaluationProvider = evaluationProvider;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    protected EntityDao<FacultyEntity> getEntityDao() {
        return facultyDao;
    }

    @Override
    public PageData<FacultyEntity> findAll(PageLink pageLink) {
        return authenticationFacade.getAuthenticationUser().map(user -> {
            Page<FacultyEntity> page = facultyDao.findAllAndNameContains(
                    DaoUtil.toPageable(pageLink, Collections.emptyMap()),
                    Objects.requireNonNullElse(pageLink.getTextSearch(), ""),
                    user
            );
            return new PageData<>(page.getContent(), page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.hasNext());
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

    @Override
    public Optional<FacultyEntity> findById(UUID id) {
        return facultyDao.findById(id);
    }

    @Override
    public FacultyEntity save(FacultyEntity entity) {
        return null;
    }

    public void saveOrUpdateFromDto(FacultyDto facultyDto) {
        if (facultyDto.getAvailableSlots() < facultyDto.getSponsoredSlots()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        FacultyEntity faculty = toFacultyEntity(facultyDto);
        Optional.ofNullable(faculty.getId()).ifPresent(id ->
                facultyDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        facultyDao.save(faculty);
    }

    public FacultyEntity toFacultyEntity(FacultyDto facultyDto) {
        FacultyEntity entity = new FacultyEntity();
        entity.setId(facultyDto.getId());
        entity.setAvailableSlots(facultyDto.getAvailableSlots());
        entity.setSponsoredSlots(facultyDto.getSponsoredSlots());
        entity.setName(facultyDto.getName());
        return entity;
    }

    public FacultyDto toFacultyDto(FacultyEntity facultyEntity) {
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setName(facultyEntity.getName());
        facultyDto.setAvailableSlots(facultyDto.getAvailableSlots());
        facultyDto.setSponsoredSlots(facultyDto.getSponsoredSlots());
        facultyDto.setId(facultyEntity.getId());
        return facultyDto;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        facultyDao.findById(id).ifPresentOrElse(faculty -> {
            for (ApplicantEntity applicant : faculty.getApplicants()) {
                applicant.getFaculties().remove(faculty);
            }
            faculty.setApplicants(Collections.emptyList());
            facultyDao.delete(faculty);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

    }

    @Override
    public void delete(FacultyEntity entity) {

    }

    public void enrollCurrentUser(UUID facultyId) {
        authenticationFacade.getAuthenticationUser().map(user -> {
            FacultyEntity facultyEntity = facultyDao.findById(facultyId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            facultyEntity.getApplicants().add(user);
            return facultyDao.save(facultyEntity);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('admin:write')")
    @Transactional
    public void finalizeFaculty(UUID id) {
        facultyDao.findById(id).ifPresentOrElse(faculty -> {
            for (ApplicantEntity applicant : faculty.getApplicants()) {
                FinalizedFacultyResultEntity result = new FinalizedFacultyResultEntity();
                result.setFaculty(faculty);
                result.setMeanScore(evaluationProvider.evaluateApplicant(applicant));
                result.setApplicant(applicant);
                result.setFinalized(true);
                finalizedResultDao.save(result);
            }
            Page<FinalizedFacultyResultEntity> page = finalizedResultDao
                    .findAllByFaculty(Pageable.unpaged(), faculty);
            List<FinalizedFacultyResultEntity> content = page.getContent();
            List<FinalizedFacultyResultEntity> sorted = content.stream()
                    .sorted(Comparator.comparing(FinalizedFacultyResultEntity::getMeanScore))
                    .collect(Collectors.toList());

            sorted.stream().limit(faculty.getSponsoredSlots())
                    .forEach(applicantResult -> applicantResult.setSponsored(true));
            sorted.stream().skip(faculty.getSponsoredSlots())
                    .forEach(applicantResult -> applicantResult.setContract(true));
            finalizedResultDao.saveAll(content);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }
}
