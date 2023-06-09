package com.example.demo.Specification;

import com.example.demo.DummyObject.DummyEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

/**
 * Specifications for building dynamic queries
 */
public class EntitySpecification {
    public static Specification<DummyEntity> withPopulation(Integer population) {

        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("population"), population));
    }

    public static Specification<DummyEntity> withPopulationInYear(Integer populationInYear) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("estimation"), populationInYear);
    }

    public static Specification<DummyEntity> withVoivodeship(String voivodeship) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), voivodeship);
    }

    public static Specification<DummyEntity> withDateRange(Date startDate, Date endDate) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("timestamp"), startDate, endDate);
    }
}
