package com.texoit.golden.awards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.texoit.golden.awards.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Integer> {
    Producer findByName(String name);
    List<Producer> findAllByOrderByNameAsc();

    @Query("SELECT p FROM Movie m JOIN m.producers p WHERE m.winner = true GROUP BY p HAVING COUNT(p) > 1 ORDER BY p.id ASC")
    List<Producer> findProducersWithMoreThanOneAward();
}
