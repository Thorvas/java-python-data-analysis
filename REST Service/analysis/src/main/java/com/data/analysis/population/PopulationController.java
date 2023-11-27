package com.data.analysis.population;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/population")
public class PopulationController {

    @Autowired
    private PopulationRepository populationRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Population>> listPopulation() {

        return new ResponseEntity<>(populationRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Population>> populationWithName(@PathVariable String name) {

        return new ResponseEntity<>(populationRepository.findWithName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/voivodeship/{voivodeship}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Population>> populationWithVoivodeship(@PathVariable String voivodeship) {

        return new ResponseEntity<>(populationRepository.findWithVoivodeship(voivodeship), HttpStatus.OK);
    }

    @GetMapping(value = "/population", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Population>> populationWithCurrentPopulation(@RequestParam Long startPopulation,
                                                                            @RequestParam Long endPopulation) {

        return new ResponseEntity<>(populationRepository.findWithPopulationBetween(startPopulation, endPopulation), HttpStatus.OK);
    }

    @GetMapping(value = "/prediction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Population>> populationWithCurrentPrediction(@RequestParam Long startPrediction,
                                                                            @RequestParam Long endPrediction) {

        return new ResponseEntity<>(populationRepository.findWithPredictionBetween(startPrediction, endPrediction), HttpStatus.OK);
    }
}
