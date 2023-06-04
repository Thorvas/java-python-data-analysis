package com.example.demo.Controller;

import com.example.demo.DummyObject.DummyEntity;
import com.example.demo.Services.DummyEntityService;
import com.example.demo.Specification.EntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private DummyEntityService service;


    @PostMapping(value = "/postEstimation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DummyEntity> postEntity(@RequestBody DummyEntity entity) {

        service.saveEntity(entity);

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @GetMapping(value = "/retrieveEstimation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<DummyEntity>> getEstimation(@RequestParam(value = "voivodeship", required = false) String voivodeship,
                                                           @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                           @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                           @RequestParam(value = "population", required = false) Integer population,
                                                           @RequestParam(value = "estimatedPopulation", required = false) Integer populationInYear,
                                                           @PageableDefault(size = 15) Pageable pageable) {

        Specification<DummyEntity> specification = Specification.where(null);

        if (Optional.ofNullable(population).isPresent()) {
            specification = specification.and(EntitySpecification.withPopulation(population));
        }
        if (Optional.ofNullable(populationInYear).isPresent()) {
            specification = specification.and(EntitySpecification.withPopulationInYear(populationInYear));
        }

        if (Optional.ofNullable(voivodeship).isPresent()) {
            specification = specification.and(EntitySpecification.withVoivodeship(voivodeship));
        }

        if (Optional.ofNullable(startDate).isPresent() && Optional.ofNullable(endDate).isPresent()) {
            specification = specification.and(EntitySpecification.withDateRange(startDate, endDate));
        }

        Page<DummyEntity> entities = service.searchEntities(specification, pageable);

        return ResponseEntity.ok(entities);
    }

    @PutMapping(value = "/entities/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DummyEntity> updateEntity(@PathVariable Long id, @RequestBody DummyEntity entity) {

        DummyEntity editedEntity = service.findEntityById(id);

        editedEntity.setCurrentPopulation(entity.getCurrentPopulation());
        editedEntity.setVoivodeship(entity.getVoivodeship());
        editedEntity.setEstimatedPopulationInYear(entity.getEstimatedPopulationInYear());
        service.saveEntity(editedEntity);

        return new ResponseEntity<>(editedEntity, HttpStatus.OK);
    }

    @DeleteMapping(value = "/entities/{id}")
    public String deleteEntity(@PathVariable Long id) {

        service.deleteEntity(id);

        return "Entity has been deleted.";
    }

}
