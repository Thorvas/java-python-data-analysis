package com.example.demo.Services;

import com.example.demo.DummyObject.DummyEntity;
import com.example.demo.Repositories.DummyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Dummy Entity service responsible for operations performed on DummyEntity objects
 */
@Service
public class DummyEntityService {
    @Autowired
    DummyEntityRepository repository;

    /**
     * Deletes an entity from database
     *
     * @param id An ID value of deleted entity
     */
    public void deleteEntity(Long id) {

        repository.deleteById(id);
    }

    /**
     * Finds an entity by ID value
     *
     * @param id An ID value of found entity
     * @return Found DummyEntity object or new DummyEntity object if it was not found
     */
    public DummyEntity findEntityById(Long id) {

        Optional<DummyEntity> entity = repository.findById(id);

        return entity.orElseGet(DummyEntity::new);
    }

    /**
     * Finds an entity by provided specification
     *
     * @param specification Specification object which contains dynamic query
     * @param pageable      Pageable object used for pagination
     * @return Page object that contains paged data
     */
    public Page<DummyEntity> searchEntities(Specification<DummyEntity> specification, Pageable pageable) {

        return repository.findAll(specification, pageable);
    }

    /**
     * Finds all entities in database
     *
     * @return List of found entities
     */
    public List<DummyEntity> searchEntities() {
        return repository.findAll();
    }

    /**
     * Saves an entity to database
     *
     * @param entity An object saved in database
     */
    public void saveEntity(DummyEntity entity) {
        repository.save(entity);
    }

    public Optional<DummyEntity> searchEntityByName(String name) {
        return repository.findByName(name);
    }
}
