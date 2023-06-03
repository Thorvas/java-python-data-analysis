package com.example.demo.Services;

import com.example.demo.DummyObject.DummyEntity;
import com.example.demo.Repositories.DummyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DummyEntityService {
    @Autowired
    DummyEntityRepository repository;

    public DummyEntity findEntityById(Long id) {
        Optional<DummyEntity> entity = repository.findById(id);
        return entity.isPresent() ? entity.get() : null;
    }

    public List<DummyEntity> findEntityByVoivodeship(String voivodeship) {
        Optional<List<DummyEntity>> foundEntity = repository.findByVoivodeship(voivodeship);
        return foundEntity.isPresent() ? foundEntity.get() : null;
    }

    public void saveEntity(DummyEntity entity) {
        repository.save(entity);
    }
}
