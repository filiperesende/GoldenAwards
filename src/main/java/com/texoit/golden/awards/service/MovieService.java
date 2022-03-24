package com.texoit.golden.awards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.golden.awards.model.Movie;
import com.texoit.golden.awards.model.Producer;
import com.texoit.golden.awards.repository.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public List<Movie> findWinnerMoviesByProducer(Producer producer) {
        return repository.findAllByWinnerTrueAndProducersOrderByYearAsc(producer);
    }
}