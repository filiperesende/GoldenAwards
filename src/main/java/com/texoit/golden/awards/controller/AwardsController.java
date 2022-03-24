package com.texoit.golden.awards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.golden.awards.dto.AwardsDTO;
import com.texoit.golden.awards.service.AwardsService;

@RestController
@RequestMapping(value = "/awards")
public class AwardsController {

    @Autowired
    private AwardsService service;

    @GetMapping
    public ResponseEntity<AwardsDTO> get() {
        AwardsDTO award = service.get();
        return ResponseEntity.ok(award);
    }
}
