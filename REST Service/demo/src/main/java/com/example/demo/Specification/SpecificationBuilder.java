package com.example.demo.Specification;

import com.example.demo.DummyObject.DummyEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;


/**
 * Class designed by Builder pattern which builds specification object passed to service method
 */
public class SpecificationBuilder {

    private Specification<DummyEntity> specification;

    public SpecificationBuilder() {

        this.specification = Specification.where(null);
    }

    public SpecificationBuilder withVoivodeship(String voivodeship) {

        if (Optional.ofNullable(voivodeship).isPresent()) {

            this.specification = specification.and(EntitySpecification.withVoivodeship(voivodeship));
        }

        return this;
    }

    public SpecificationBuilder withPopulation(Integer population) {

        if (Optional.ofNullable(population).isPresent()) {

            this.specification = specification.and(EntitySpecification.withPopulation(population));
        }

        return this;
    }

    public SpecificationBuilder withPopulationInYear(Integer populationInYear) {

        if (Optional.ofNullable(populationInYear).isPresent()) {

            this.specification = specification.and(EntitySpecification.withPopulationInYear(populationInYear));
        }

        return this;
    }

    public Specification<DummyEntity> buildSpecification() {

        return this.specification;
    }
}
