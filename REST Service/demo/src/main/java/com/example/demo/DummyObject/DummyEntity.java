package com.example.demo.DummyObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

/**
 * An entity containing analyzed data
 *
 * @author Thorvas
 */
@Entity
@Table(name="DUMMY_ENTITY")
public class DummyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "voivodeship")
    private String voivodeship;
    @Column(name = "current_population")
    private int currentPopulation;

    @Column(name = "estimated_population")
    private int estimatedPopulationInYear;

    @Column(name = "timestamp")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date timestamp;

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
