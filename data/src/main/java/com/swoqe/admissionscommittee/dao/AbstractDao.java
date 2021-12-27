package com.swoqe.admissionscommittee.dao;

import com.swoqe.admissionscommittee.entity.BaseSqlEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public abstract class AbstractDao<T extends BaseSqlEntity, R extends JpaRepository<T, UUID>> {

    protected R repository;

    public AbstractDao(R repository) {
        this.repository = repository;
    }

    public Page<T> findAll(Pageable pageable) {
        Page<T> all = repository.findAll(pageable);
        log.debug("DAO | Request to find page, found: {}", all.getSize());
        return all;
    }

    public Optional<T> findById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        Optional<T> byId = repository.findById(id);
        log.debug("DAO | Request to find by id: {}", byId );
        return byId;
    }

    public T save(T object) {
        if (object == null) {
            return null;
        }
        if (object.getId() == null) {
            object.setId(UUID.randomUUID());
        }
        T save = repository.save(object);
        log.debug("DAO | Persisted entity with id: {}", save.getId());
        return save;
    }

    @Transactional
    public void delete(T object) {
        if (object == null || object.getId() == null) {
            return;
        }
        repository.delete(object);
        log.debug("DAO | Request to delete entity with id: {}", object.getId());
    }

    @Transactional
    public void deleteById(UUID id) {
        if (id == null) {
            return;
        }
        repository.deleteById(id);
        log.debug("DAO | Request to delete entity with id: {}", id);
    }


}
