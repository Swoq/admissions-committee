package com.swoqe.admissionscommittee.service;

import com.swoqe.admissionscommittee.dao.EntityDao;
import com.swoqe.admissionscommittee.entity.BaseSqlEntity;

public abstract class AbstractService<E extends BaseSqlEntity> {

    protected abstract EntityDao<E> getEntityDao();

}
