package com.example.demo.Repositories;


import com.example.demo.DummyObject.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DummyEntityRepository extends JpaRepository<DummyEntity, Long> {
    Optional<List<DummyEntity>> findByVoivodeship(String voivodeship);
}
