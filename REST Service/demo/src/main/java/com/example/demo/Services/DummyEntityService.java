package com.example.demo.Services;

import com.example.demo.DummyObject.DummyEntity;
import com.example.demo.Repositories.DummyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DummyEntityService {
    @Autowired
    DummyEntityRepository repository;

    public void deleteEntity(Long id) {

        repository.deleteById(id);
    }

    public DummyEntity findEntityById(Long id) {

        Optional<DummyEntity> entity = repository.findById(id);

        return entity.orElseGet(DummyEntity::new);
    }

    public Page<DummyEntity> searchEntities(Specification<DummyEntity> specification, Pageable pageable) {

        return repository.findAll(specification, pageable);
    }

    public List<DummyEntity> searchEntities() {
        return repository.findAll();
    }

    public void saveEntity(DummyEntity entity) {
        repository.save(entity);
    }
}
