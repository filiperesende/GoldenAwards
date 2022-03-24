package com.texoit.golden.awards.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.golden.awards.model.Producer;
import com.texoit.golden.awards.repository.ProducerRepository;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository repository;

    public List<Producer> findByNames(String names) {
        if (names == null)
            return Collections.emptyList();

        String[] split = names.split(",");
        List<Producer> producers = new ArrayList<>();
        for (String item : split) {
            String name = StringUtils.trim(item);
            Producer producer = repository.findByName(name);
            if (producer == null) {
                producer = create(name);
            }

            producers.add(producer);
        }

        return producers;
    }

    private Producer create(String name) {
        Producer producer = new Producer();
        producer.setName(name);
        return repository.save(producer);
    }

    public List<Producer> findProducersWithMoreThanOneAward() {
        return repository.findProducersWithMoreThanOneAward();
    }
}