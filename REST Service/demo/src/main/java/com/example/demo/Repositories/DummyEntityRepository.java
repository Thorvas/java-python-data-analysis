package com.example.demo.Repositories;


import com.example.demo.DummyObject.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DummyEntityRepository extends JpaRepository<DummyEntity, Long> {

    @Query("SELECT e FROM DummyEntity e WHERE e.voivodeship = :voivodeship AND e.timestamp BETWEEN :startDate AND :endDate")
    Optional<List<DummyEntity>> findAllByVoivodeshipAndTimestampBetween(String voivodeship, Date startDate, Date endDate);
}
