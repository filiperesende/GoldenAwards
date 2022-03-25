package com.texoit.golden.awards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.golden.awards.model.Movie;
import com.texoit.golden.awards.service.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MoviesController {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<Movie>> get() {
        List<Movie> all = service.get();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Integer id) {
        Movie movie = service.getById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/winners")
    public ResponseEntity<List<Movie>> getWinners() {
        List<Movie> winners = service.getWinners();
        return ResponseEntity.ok(winners);
    }

}
