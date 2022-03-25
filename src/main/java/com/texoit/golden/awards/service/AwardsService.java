package com.texoit.golden.awards.service;

import java.util.ArrayList;
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
        if (awards.getMin().isEmpty() || isSameInterval(awards.getMin(), dto)) {
            awards.getMin().add(dto);
        } else if (isLess(awards, dto)) {
            awards.setMin(new ArrayList<>());
            awards.getMin().add(dto);
        }
    }

    private void addMax(AwardsDTO awards, ProducerAwardDTO dto) {
        if (awards.getMax().isEmpty() || isSameInterval(awards.getMax(), dto)) {
            awards.getMax().add(dto);
        } else if (isGreater(awards, dto)) {
            awards.setMax(new ArrayList<>());
            awards.getMax().add(dto);
        }
    }

    private boolean isLess(AwardsDTO awards, ProducerAwardDTO dto) {
        for (ProducerAwardDTO item : awards.getMin()) {
            if (dto.getInterval() < item.getInterval())
                return true;
        }

        return false;
    }

    private boolean isGreater(AwardsDTO awards, ProducerAwardDTO dto) {
        for (ProducerAwardDTO item : awards.getMax()) {
            if (dto.getInterval() > item.getInterval())
                return true;
        }

        return false;
    }

    private boolean isSameInterval(List<ProducerAwardDTO> producers, ProducerAwardDTO dto) {
        for (ProducerAwardDTO item : producers) {
            if (dto.getInterval().equals(item.getInterval()))
                return true;
        }

        return false;
    }

    private ProducerAwardDTO createDto(Producer producer, List<Movie> movies, boolean isMin) {
        Integer[] years = isMin ? getMinDiffYears(movies) : getMaxDiffYears(movies);

        ProducerAwardDTO dto = new ProducerAwardDTO();
        dto.setProducer(producer.getName());
        dto.setPreviousWin(years[0]);
        dto.setFollowingWin(years[1]);
        dto.setInterval(years[1] - years[0]);
        return dto;
    }

    private Integer[] getMinDiffYears(List<Movie> movies) {
        int diff = Integer.MAX_VALUE;
        Integer first = null;
        Integer last = null;
        for (int i = 0; i <= movies.size() - 2; i++) {
            Movie itemFirst = movies.get(i);
            Movie itemLast = movies.get(i + 1);
            int itemDiff = itemLast.getYear() - itemFirst.getYear();
            if (itemDiff < diff) {
                diff = itemDiff;
                first = itemFirst.getYear();
                last = itemLast.getYear();
            }
        }

        return new Integer[] { first, last };
    }

    private Integer[] getMaxDiffYears(List<Movie> movies) {
        int diff = -1;
        Integer first = null;
        Integer last = null;
        for (int i = 0; i <= movies.size() - 2; i++) {
            Movie itemFirst = movies.get(i);
            Movie itemLast = movies.get(i + 1);
            int itemDiff = itemLast.getYear() - itemFirst.getYear();
            if (itemDiff > diff) {
                diff = itemDiff;
                first = itemFirst.getYear();
                last = itemLast.getYear();
            }
        }

        return new Integer[] { first, last };
    }

}
