package com.texoit.golden.awards.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.golden.awards.model.Studio;
import com.texoit.golden.awards.repository.StudioRepository;

@Service
public class StudioService {

    @Autowired
    private StudioRepository repository;

    public List<Studio> findByNames(String names) {
        if (names == null)
            return Collections.emptyList();

        String[] split = names.split(",");
        ArrayList<Studio> studios = new ArrayList<>();
        for (String item : split) {
            String name = StringUtils.trim(item);
            Studio studio = repository.findByName(name);
            if (studio == null) {
                studio = create(name);
            }

            studios.add(studio);
        }

        return studios;
    }

    private Studio create(String name) {
        Studio studio = new Studio();
        studio.setName(name);
        return repository.save(studio);
    }
}