package com.swoqe.admissionscommittee.dao;

import java.util.List;
import java.util.UUID;

public interface EntityDao<T> {
    List<T> findAll();

    T findById(UUID id);

    T save(T entity);

    void deleteById(UUID id);

    void delete(T entity);

}
