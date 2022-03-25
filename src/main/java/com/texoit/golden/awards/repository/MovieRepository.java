package com.texoit.golden.awards.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.texoit.golden.awards.model.Movie;
import com.texoit.golden.awards.model.Producer;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findAllByWinnerTrueAndProducersOrderByYearAsc(Producer producer);

    @Modifying
    @Transactional
    @Query(value = "UPDATE movie_producer SET producer_id = :producerSetId WHERE movie_id = :movieId AND producer_id = :producerWhereId", nativeQuery = true)
    void updateMovieProducer(Integer producerSetId, Integer movieId, Integer producerWhereId);
}
