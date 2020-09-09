package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhisky(@RequestParam(name="year", required = false) Integer year, @RequestParam(name="distillery", required=false) String distillery, @RequestParam(name="age", required =false) Integer age) {
        if (age != null && distillery != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distillery, age), HttpStatus.OK);
        }
        if (age != null) {
            return new ResponseEntity<>(whiskyRepository.findByAge(age), HttpStatus.OK);
        }
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        if (distillery != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryName(distillery), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}
