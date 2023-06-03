package com.example.demo.DummyObject;

import jakarta.persistence.*;

@Entity
public class DummyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "voivodeship")
    private String voivodeship;

    @Column(name = "currentPopulation")
    private int currentPopulation;

    @Column(name = "estimatedPopulation")
    private int estimatedPopulationInYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public int getCurrentPopulation() {
        return currentPopulation;
    }

    public void setCurrentPopulation(int currentPopulation) {
        this.currentPopulation = currentPopulation;
    }

    public int getEstimatedPopulationInYear() {
        return estimatedPopulationInYear;
    }

    public void setEstimatedPopulationInYear(int estimatedPopulationInYear) {
        this.estimatedPopulationInYear = estimatedPopulationInYear;
    }
}
