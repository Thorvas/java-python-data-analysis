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

    @Column(name = "name")
    private String name;
    @Column(name = "population")
    private int population;

    @Column(name = "estimation")
    private int estimation;

    @Column(name = "timestamp")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
