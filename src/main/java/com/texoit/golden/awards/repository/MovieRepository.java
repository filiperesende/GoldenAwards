package com.texoit.golden.awards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoit.golden.awards.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
