package com.swoqe.admissionscommittee.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EntityDao<T> {
    Page<T> findAll(Pageable pageable);

    Optional<T> findById(UUID id);

    T save(T entity);

    void deleteById(UUID id);

    void delete(T entity);

}
