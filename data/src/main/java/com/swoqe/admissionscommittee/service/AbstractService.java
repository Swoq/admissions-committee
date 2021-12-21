package com.swoqe.admissionscommittee.service;

import com.swoqe.admissionscommittee.dao.EntityDao;
import com.swoqe.admissionscommittee.entity.BaseSqlEntity;

public abstract class AbstractService<E extends BaseSqlEntity> {

    protected abstract EntityDao<E> getEntityDao();

//    protected D convertToDto(E entity, Class<D> eClass) {
//        return modelMapper.map(entity, eClass);
//    }
//
//    protected E convertToEntity(D dto, Class<E> eClass) {
//        E entity = modelMapper.map(dto, eClass);
//        getEntityDao().findById(dto.getId()).ifPresent(found -> {
//            entity.setId(found.getId());
//            entity.setCreatedTime(found.getCreatedTime());
//        });
//        return entity;
//    }

}
