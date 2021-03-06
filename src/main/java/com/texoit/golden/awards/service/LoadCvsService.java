package com.texoit.golden.awards.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.texoit.golden.awards.model.Movie;
import com.texoit.golden.awards.model.Producer;
import com.texoit.golden.awards.model.Studio;
import com.texoit.golden.awards.repository.MovieRepository;

@Service
public class LoadCvsService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private StudioService studioService;

    @Autowired
    private ProducerService producerService;

    @PostConstruct
    public void postConstruct() {
        InputStream stream = getClass().getResourceAsStream("/movielist.csv");
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';')
                                                    .build();
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(stream)).withCSVParser(csvParser)
                                                                                   .withSkipLines(1)
                                                                                   .build()) {
            List<String[]> r = reader.readAll();
            r.forEach(this::createMovie);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void createMovie(String[] line) {
        Movie movie = new Movie();
        movie.setYear(Integer.parseInt(line[0]));
        movie.setTitle(line[1]);
        setStudios(line[2], movie);
        setProducers(line[3], movie);
        movie.setWinner("yes".equalsIgnoreCase(line[4]));
        repository.save(movie);
    }

    private void setProducers(String names, Movie movie) {
        List<Producer> producers = producerService.findByNames(names);
        movie.setProducers(producers);
    }

    private void setStudios(String names, Movie movie) {
        List<Studio> studios = studioService.findByNames(names);
        movie.setStudios(studios);
    }
}
