package com.example.demo.Controller;

import com.example.demo.DummyObject.DummyEntity;
import com.example.demo.Mapper.DummyEntityMapper;
import com.example.demo.Services.DummyEntityService;
import com.example.demo.Specification.SpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * Controller for handling DummyEntity requests.
 *
 * @author Thorvas
 */
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private DummyEntityService service;

    /**
     * Receives data from logic part of application and saves received data to database
     *
     * @param entity An entity sent to endpoint from data-analysis module which is saved to database
     * @return The ResponseEntity object which contains saved entity
     */
    @PostMapping(value = "/postEstimation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DummyEntity> postEntity(@RequestBody DummyEntity entity) {

        System.out.println(entity.getId());

        Optional<DummyEntity> foundEntity = service.searchEntityById(entity.getId());
        System.out.println(entity.getId());
        if (foundEntity.isPresent()) {
            DummyEntity presentEntity = foundEntity.get();
            DummyEntityMapper.mapEntity(entity, presentEntity);
            service.saveEntity(presentEntity);

        } else {

            service.saveEntity(entity);
        }

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    /**
     * Retrieves estimation data from database based on parameters provided for filtering.
     *
     * @param voivodeship      The voivodeship value (as String)
     * @param startDate        The start date value in range (in format yyyy-MM-dd)
     * @param endDate          The end date value in range (in format yyyy-MM-dd)
     * @param population       The population value
     * @param populationInYear The estimated value for population in year
     * @param pageable         The Pageable object for pagination
     * @return The ResponseEntity containing result of search
     */
    @GetMapping(value = "/retrieveEstimation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<DummyEntity>> getEstimation(@RequestParam(value = "voivodeship", required = false) String voivodeship,
                                                           @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                           @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                           @RequestParam(value = "population", required = false) Integer population,
                                                           @RequestParam(value = "estimatedPopulation", required = false) Integer populationInYear,
                                                           @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {

        SpecificationBuilder specificationBuilder = new SpecificationBuilder();

        specificationBuilder.withPopulation(population)
                .withDateRange(startDate, endDate)
                .withVoivodeship(voivodeship)
                .withPopulationInYear(populationInYear);

        Page<DummyEntity> entities = service.searchEntities(specificationBuilder.buildSpecification(), pageable);

        return ResponseEntity.ok(entities);
    }

    /**
     * Updates an entity in database
     *
     * @param id     An ID value of updated object
     * @param entity New object to substitute an updated objec
     * @return The ResponseEntity object containing updated object
     */
    @PatchMapping(value = "/entities/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DummyEntity> updateEntity(@PathVariable Long id, @RequestBody DummyEntity entity) {

        DummyEntity editedEntity = service.findEntityById(id);

        DummyEntityMapper.mapEntity(entity, editedEntity);
        service.saveEntity(editedEntity);

        return new ResponseEntity<>(editedEntity, HttpStatus.OK);
    }

    /**
     * Deletes an entity from database
     *
     * @param id An ID value of deleted object
     * @return The String with deletion message
     */
    @DeleteMapping(value = "/entities/{id}")
    public String deleteEntity(@PathVariable Long id) {

        service.deleteEntity(id);

        return "Entity has been deleted.";
    }
}
