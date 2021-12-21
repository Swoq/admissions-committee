package com.swoqe.admissionscommittee.service;

import com.swoqe.admissionscommittee.page.PageData;
import com.swoqe.admissionscommittee.page.PageLink;

import java.util.Optional;
import java.util.UUID;

public interface Service<T> {
    PageData<T> findAll(PageLink pageLink);

    Optional<T> findById(UUID id);

    T save(T entity);

    void deleteById(UUID id);

    void delete(T entity);
}
