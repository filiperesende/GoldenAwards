package com.texoit.golden.awards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.golden.awards.model.Producer;
import com.texoit.golden.awards.service.ProducerService;

@RestController
@RequestMapping(value = "/producers")
public class ProducersController {

    @Autowired
    private ProducerService service;

    @GetMapping
    public ResponseEntity<List<Producer>> get() {
        List<Producer> all = service.get();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producer> getById(@PathVariable Integer id) {
        Producer movie = service.getById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/more_than_one_award")
    public ResponseEntity<List<Producer>> getMoreThanOneAward() {
        List<Producer> producers = service.findProducersWithMoreThanOneAward();
        return ResponseEntity.ok(producers);
    }

}
