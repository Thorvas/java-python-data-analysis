package com.example.demo.Controller;

import com.example.demo.DummyObject.DummyEntity;
import com.example.demo.Services.DummyEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private DummyEntityService service;

    @PostMapping(value = "/postEstimation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DummyEntity> postEntity(@RequestBody DummyEntity entity) {

        DummyEntity newEntity = entity;

        service.saveEntity(entity);

        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);
    }

    @GetMapping(value = "/retrieveEstimation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DummyEntity>> getEstimation(@RequestParam("voivodeship") String voivodeship, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                         @RequestParam("endDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        List<DummyEntity> entity = service.findEntityByVoivodeshipAndDate(voivodeship, startDate, endDate);

        return new ResponseEntity<>(entity, HttpStatus.OK);
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

}
