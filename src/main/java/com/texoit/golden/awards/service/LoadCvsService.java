package com.texoit.golden.awards.service;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
import com.texoit.golden.awards.model.Studio;
import com.texoit.golden.awards.repository.MovieRepository;

@Service
public class LoadCvsService {

    // private List<MovieCvs> movies;

    @Autowired
    private MovieRepository repository;

    @Autowired
    private StudioService studioService;

    @PostConstruct
    public void postConstruct() {
        // movies = new ArrayList<>();
        URL resource = getClass().getResource("/movielist.csv");
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(resource.getFile())).withCSVParser(csvParser).withSkipLines(1).build()) {
            List<String[]> r = reader.readAll();
            r.forEach(this::createMovie);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void createMovie(String[] line) {
        Movie movie = new Movie();
        movie.setYear(line[0]);
        movie.setTitle(line[1]);
        setStudios(line[2], movie);
        movie.setProducers(line[3]);
        movie.setWinner("yes".equalsIgnoreCase(line[4]));
        repository.save(movie);
    }

    private void setStudios(String names, Movie movie) {
        List<Studio> studios = studioService.findByNames(names);
        movie.setStudios(studios);
    }
}
