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

    public void updateMovieProducer(Integer producertSetId, Integer movieId, Integer producerWhereId) {
        repository.updateMovieProducer(producertSetId, movieId, producerWhereId);
    }

    public void resetDatabase() {
        repository.updateMovieProducer(1, 1, 26);
        repository.updateMovieProducer(11, 11, 26);
        repository.updateMovieProducer(60, 66, 52);
        repository.updateMovieProducer(288, 202, 16);
        repository.updateMovieProducer(34, 36, 29);
        repository.updateMovieProducer(178, 156, 61);
    }

    public List<Movie> get() {
        return repository.findAllByOrderByYearAsc();
    }

    public List<Movie> getWinners() {
        return repository.findAllByWinnerTrueOrderByYearAsc();
    }

    public Movie getById(Integer id) {
        if (id == null)
            return null;

        return repository.getOne(id);
    }

}