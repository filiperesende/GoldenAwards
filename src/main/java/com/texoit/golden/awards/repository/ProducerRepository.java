package com.texoit.golden.awards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoit.golden.awards.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Integer> {
    Producer findByName(String name);
}
