package com.example.demo.Controller;

import com.example.demo.DummyObject.DummyEntity;
import com.example.demo.Services.DummyEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<DummyEntity>> getEstimation(@RequestParam String voivodeship) {
        List<DummyEntity> entity = service.findEntityByVoivodeship(voivodeship);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
