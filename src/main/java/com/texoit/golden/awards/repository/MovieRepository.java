package com.texoit.golden.awards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoit.golden.awards.model.Movie;
import com.texoit.golden.awards.model.Producer;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findAllByWinnerTrueAndProducersOrderByYearAsc(Producer producer);
}
