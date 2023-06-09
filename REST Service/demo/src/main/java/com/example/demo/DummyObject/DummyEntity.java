package com.example.demo.DummyObject;

import com.fasterxml.jackson.annotation.JsonFormat;
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
        this.name = dummyEntity.name;
        this.population = dummyEntity.population;
        this.estimation = dummyEntity.estimation;
        this.lastUpdated = dummyEntity.lastUpdated;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;
    @ElementCollection
    private Map<String, Integer> population;

    @Column(name = "estimation")
    private int estimation;
    @Column(name = "last_updated")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastUpdated;

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

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
