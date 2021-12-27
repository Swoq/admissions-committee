package com.swoqe.admissionscommittee.dao;

import com.swoqe.admissionscommittee.entity.BaseSqlEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractDao<T extends BaseSqlEntity, R extends JpaRepository<T, UUID>> {

    protected R repository;

    public AbstractDao(R repository) {
        this.repository = repository;
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<T> findById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        return repository.findById(id);
    }

    public T save(T object) {
        if (object == null) {
            return null;
        }
        if (object.getId() == null) {
            object.setId(UUID.randomUUID());
        }
        return repository.save(object);
    }

    @Transactional
    public void delete(T object) {
        if (object == null || object.getId() == null) {
            return;
        }
        repository.delete(object);
    }

    @Transactional
    public void deleteById(UUID id) {
        if (id == null) {
            return;
        }
        repository.deleteById(id);
    }


}
