package com.texoit.golden.awards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoit.golden.awards.model.Studio;

public interface StudioRepository extends JpaRepository<Studio, Integer> {
    Studio findByName(String name);
}
