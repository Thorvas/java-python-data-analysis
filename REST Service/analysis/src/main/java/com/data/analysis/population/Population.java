package com.data.analysis.population;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "poviat")
@Entity
public class Population {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "voivodeship")
    private String voivodeship;

    @Column(name = "population")
    private Long population;

    @Column(name = "prediction")
    private Long prediction;

    @Column(name = "last_updated")
    private LocalDate updateDate;
}
