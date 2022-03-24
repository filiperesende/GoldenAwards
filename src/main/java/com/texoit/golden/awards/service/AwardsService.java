package com.texoit.golden.awards.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.golden.awards.dto.AwardsDTO;
import com.texoit.golden.awards.dto.ProducerAwardDTO;
import com.texoit.golden.awards.model.Movie;
import com.texoit.golden.awards.model.Producer;

@Service
public class AwardsService {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private MovieService movieService;

    public AwardsDTO get() {
        List<Producer> producers = producerService.findProducersWithMoreThanOneAward();

        AwardsDTO awards = new AwardsDTO();
        for (Producer item : producers) {
            List<Movie> movies = movieService.findWinnerMoviesByProducer(item);
            if (CollectionUtils.isEmpty(movies))
                continue;

            ProducerAwardDTO dtoMin = createDto(item, movies, true);
            addMin(awards, dtoMin);

            ProducerAwardDTO dtoMax = createDto(item, movies, false);
            addMax(awards, dtoMax);
        }

        return awards;
    }

    private void addMin(AwardsDTO awards, ProducerAwardDTO dto) {
        if (isMinOrEqual(awards, dto))
            awards.getMin().add(dto);
    }

    private void addMax(AwardsDTO awards, ProducerAwardDTO dto) {
        if (isMaxOrEqual(awards, dto))
            awards.getMax().add(dto);
    }

    private boolean isMinOrEqual(AwardsDTO awards, ProducerAwardDTO dto) {
        if (awards.getMin().isEmpty())
            return true;

        for (ProducerAwardDTO item : awards.getMin()) {
            if (dto.getInterval() <= item.getInterval() && !dto.getProducer().equals(item.getProducer()))
                return true;
        }

        return false;
    }

    private boolean isMaxOrEqual(AwardsDTO awards, ProducerAwardDTO dto) {
        if (awards.getMin().isEmpty())
            return true;

        for (ProducerAwardDTO item : awards.getMin()) {
            if (dto.getInterval() >= item.getInterval() && !dto.getProducer().equals(item.getProducer()))
                return true;
        }

        return false;
    }

    private ProducerAwardDTO createDto(Producer producer, List<Movie> movies, boolean isMin) {
        Movie first = getFirst(movies);
        Movie last = getLast(movies, isMin);

        ProducerAwardDTO dto = new ProducerAwardDTO();
        dto.setProducer(producer.getName());
        dto.setPreviousWin(first.getYear());
        dto.setFollowingWin(last.getYear());
        dto.setInterval(last.getYear() - first.getYear());
        return dto;
    }

    private Movie getFirst(List<Movie> movies) {
        return movies.get(0);
    }

    private Movie getLast(List<Movie> movies, boolean isMin) {
        if (isMin)
            return movies.get(1);

        return movies.get(movies.size() - 1);
    }

}
