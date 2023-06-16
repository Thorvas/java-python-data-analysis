package com.example.demo.DummyObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * An entity containing analyzed data
 *
 * @author Thorvas
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "DUMMY_ENTITY")
public class DummyEntity {


    public DummyEntity(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DummyEntity dummyEntity = objectMapper.readValue(json, DummyEntity.class);
        this.id = dummyEntity.getId();
        this.name = dummyEntity.name;
        this.population = dummyEntity.population;
        this.prediction = dummyEntity.prediction;
        this.lastUpdated = dummyEntity.lastUpdated;
        this.voivodeship = dummyEntity.voivodeship;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "voivodeship", nullable = false)
    private String voivodeship;

    @ElementCollection
    private Map<String, Integer> population;

    @NotBlank
    @Min(value = 0)
    @Column(name = "prediction", nullable = false)
    private int prediction;


    @Column(name = "last_updated", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("last_updated")
    private Date lastUpdated;
}
