package com.example.demo.Services;

import com.example.demo.DummyObject.DummyEntity;
import com.example.demo.Repositories.DummyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DummyEntityService {
    @Autowired
    DummyEntityRepository repository;

    public DummyEntity findEntityById(Long id) {

        Optional<DummyEntity> entity = repository.findById(id);

        return entity.orElse(new DummyEntity());
    }

    public List<DummyEntity> findEntityByVoivodeshipAndDate(String voivodeship, Date startDate, Date endDate) {

        Optional<List<DummyEntity>> foundEntity = repository.findAllByVoivodeshipAndTimestampBetween(voivodeship, startDate, endDate);

        return foundEntity.orElse(Collections.emptyList());
    }

    public void saveEntity(DummyEntity entity) {
        repository.save(entity);
    }
}
