package com.data.analysis.population;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Long> {

    @Query("SELECT p FROM Population p WHERE p.name = :name")
    List<Population> findWithName(String name);

    @Query("SELECT p FROM Population p WHERE p.voivodeship = :voivodeship")
    List<Population> findWithVoivodeship(String voivodeship);

    @Query("SELECT p FROM Population p WHERE p.population BETWEEN :startPopulation AND :endPopulation")
    List<Population> findWithPopulationBetween(Long startPopulation, Long endPopulation);

    @Query("SELECT p FROM Population p WHERE p.prediction BETWEEN :startPrediction AND :endPrediction")
    List<Population> findWithPredictionBetween(Long startPrediction, Long endPrediction);


}
