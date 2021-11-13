package com.swoqe.admissionscommittee.dao;

import com.swoqe.admissionscommittee.entity.BaseSqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public abstract class AbstractDao<T extends BaseSqlEntity, R extends JpaRepository<T, UUID>> {

    protected R repository;

    public AbstractDao(R repository) {
        this.repository = repository;
    }

    public final List<T> findAll() {
        return repository.findAll();
    }

    public final T findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Object id:" + id + " not found"));
    }

    @Transactional
    public T save(T object) {
        if (object.getId() == null) {
            object.setId(UUID.randomUUID());
        }
        if (object.getCreatedTime() == 0) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            object.setCreatedTime(timestamp.getTime());
        }
        return repository.save(object);
    }

    @Transactional
    public void delete(T object) {
        if (object.getId() == null || !repository.existsById(object.getId())) {
            throw new IllegalArgumentException("Object not found");
        }
        repository.delete(object);
    }

    @Transactional
    public void deleteById(UUID id) {
        if (id == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Object not found");
        }
        repository.deleteById(id);
    }


}
