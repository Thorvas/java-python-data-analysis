package com.example.demo.DummyObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * An entity containing analyzed data
 *
 * @author Thorvas
 */
@Entity
@Table(name="DUMMY_ENTITY")
public class DummyEntity {

    public DummyEntity() {

    }

    public DummyEntity(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DummyEntity dummyEntity = objectMapper.readValue(json, DummyEntity.class);
        this.id = dummyEntity.getId();
        this.name = dummyEntity.name;
        this.population = dummyEntity.population;
        this.prediction = dummyEntity.prediction;
        this.lastUpdated = dummyEntity.lastUpdated;
        this.voivodeship = dummyEntity.getVoivodeship();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "voivodeship")
    private String voivodeship;

    @ElementCollection
    private Map<String, Integer> population;

    @Column(name = "prediction")
    private int prediction;
    @Column(name = "last_updated")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("last_updated")
    private Date lastUpdated;

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getPopulation() {
        return population;
    }

    public void setPopulation(Map<String, Integer> population) {
        this.population = population;
    }

    public int getprediction() {
        return prediction;
    }

    public void setprediction(int prediction) {
        this.prediction = prediction;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
