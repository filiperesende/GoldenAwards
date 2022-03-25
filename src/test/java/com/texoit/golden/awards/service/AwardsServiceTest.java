package com.texoit.golden.awards.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.texoit.golden.awards.dto.AwardsDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AwardsServiceTest {

    @Autowired
    private AwardsService service;

    @Autowired
    private MovieService movieService;

    @BeforeEach
    void before() {
        movieService.resetDatabase();
    }

    @Test
    void shouldRetornAwardDTOWithOneMinIntervalSixAndOneMaxIntervalThirteen() {
        AwardsDTO dto = service.get();

        assertThat(dto, notNullValue());
        assertThat(dto.getMin(), notNullValue());
        assertThat(dto.getMin().size(), equalTo(1));
        assertThat(dto.getMin().get(0).getPreviousWin(), equalTo(1984));
        assertThat(dto.getMin().get(0).getFollowingWin(), equalTo(1990));
        assertThat(dto.getMin().get(0).getInterval(), equalTo(6));
        assertThat(dto.getMin().get(0).getProducer(), equalTo("Bo Derek"));

        assertThat(dto.getMax(), notNullValue());
        assertThat(dto.getMax().size(), equalTo(1));
        assertThat(dto.getMax().get(0).getPreviousWin(), equalTo(2002));
        assertThat(dto.getMax().get(0).getFollowingWin(), equalTo(2015));
        assertThat(dto.getMax().get(0).getInterval(), equalTo(13));
        assertThat(dto.getMax().get(0).getProducer(), equalTo("Matthew Vaughn"));
    }

    @Test
    void shouldRetornAwardDTOWithOneMinIntervalOneAndOneMaxIntervalThirteen() {
        movieService.updateMovieProducer(26, 1, 1);
        movieService.updateMovieProducer(26, 11, 11);

        AwardsDTO dto = service.get();

        assertThat(dto, notNullValue());
        assertThat(dto.getMin(), notNullValue());
        assertThat(dto.getMin().size(), equalTo(1));
        assertThat(dto.getMin().get(0).getPreviousWin(), equalTo(1980));
        assertThat(dto.getMin().get(0).getFollowingWin(), equalTo(1981));
        assertThat(dto.getMin().get(0).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(0).getProducer(), equalTo("Bo Derek"));

        assertThat(dto.getMax(), notNullValue());
        assertThat(dto.getMax().size(), equalTo(1));
        assertThat(dto.getMax().get(0).getPreviousWin(), equalTo(2002));
        assertThat(dto.getMax().get(0).getFollowingWin(), equalTo(2015));
        assertThat(dto.getMax().get(0).getInterval(), equalTo(13));
        assertThat(dto.getMax().get(0).getProducer(), equalTo("Matthew Vaughn"));
    }

    @Test
    void shouldRetornAwardDTOWithOneMinIntervalTwoAndOneMaxIntervalThirteen() {
        movieService.updateMovieProducer(52, 66, 60);

        AwardsDTO dto = service.get();

        assertThat(dto, notNullValue());
        assertThat(dto.getMin(), notNullValue());
        assertThat(dto.getMin().size(), equalTo(1));
        assertThat(dto.getMin().get(0).getPreviousWin(), equalTo(1990));
        assertThat(dto.getMin().get(0).getFollowingWin(), equalTo(1992));
        assertThat(dto.getMin().get(0).getInterval(), equalTo(2));
        assertThat(dto.getMin().get(0).getProducer(), equalTo("Steven Perry and Joel Silver"));

        assertThat(dto.getMax(), notNullValue());
        assertThat(dto.getMax().size(), equalTo(1));
        assertThat(dto.getMax().get(0).getPreviousWin(), equalTo(2002));
        assertThat(dto.getMax().get(0).getFollowingWin(), equalTo(2015));
        assertThat(dto.getMax().get(0).getInterval(), equalTo(13));
        assertThat(dto.getMax().get(0).getProducer(), equalTo("Matthew Vaughn"));
    }

    @Test
    void shouldRetornAwardDTOWithTwoMinOneMaxIntervalThirtySeven() {
        movieService.updateMovieProducer(26, 1, 1);
        movieService.updateMovieProducer(26, 11, 11);
        movieService.updateMovieProducer(29, 36, 34);
        movieService.updateMovieProducer(52, 66, 60);
        movieService.updateMovieProducer(16, 202, 288);

        AwardsDTO dto = service.get();

        assertThat(dto, notNullValue());
        assertThat(dto.getMin(), notNullValue());
        assertThat(dto.getMin().size(), equalTo(2));
        assertThat(dto.getMin().get(0).getPreviousWin(), equalTo(1980));
        assertThat(dto.getMin().get(0).getFollowingWin(), equalTo(1981));
        assertThat(dto.getMin().get(0).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(0).getProducer(), equalTo("Bo Derek"));

        assertThat(dto.getMin().get(1).getPreviousWin(), equalTo(1985));
        assertThat(dto.getMin().get(1).getFollowingWin(), equalTo(1986));
        assertThat(dto.getMin().get(1).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(1).getProducer(), equalTo("Buzz Feitshans"));

        assertThat(dto.getMax(), notNullValue());
        assertThat(dto.getMax().size(), equalTo(1));
        assertThat(dto.getMax().get(0).getPreviousWin(), equalTo(1982));
        assertThat(dto.getMax().get(0).getFollowingWin(), equalTo(2019));
        assertThat(dto.getMax().get(0).getInterval(), equalTo(37));
        assertThat(dto.getMax().get(0).getProducer(), equalTo("Mitsuharu Ishii"));
    }

    @Test
    void shouldRetornAwardDTOWithTwoMinAndTwoMax() {
        movieService.updateMovieProducer(26, 1, 1);
        movieService.updateMovieProducer(26, 11, 11);
        movieService.updateMovieProducer(29, 36, 34);
        movieService.updateMovieProducer(52, 66, 60);
        movieService.updateMovieProducer(61, 156, 178);

        AwardsDTO dto = service.get();

        assertThat(dto, notNullValue());
        assertThat(dto.getMin(), notNullValue());
        assertThat(dto.getMin().size(), equalTo(2));
        assertThat(dto.getMin().get(0).getPreviousWin(), equalTo(1980));
        assertThat(dto.getMin().get(0).getFollowingWin(), equalTo(1981));
        assertThat(dto.getMin().get(0).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(0).getProducer(), equalTo("Bo Derek"));

        assertThat(dto.getMin().get(1).getPreviousWin(), equalTo(1985));
        assertThat(dto.getMin().get(1).getFollowingWin(), equalTo(1986));
        assertThat(dto.getMin().get(1).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(1).getProducer(), equalTo("Buzz Feitshans"));

        assertThat(dto.getMax(), notNullValue());
        assertThat(dto.getMax().size(), equalTo(2));
        assertThat(dto.getMax().get(0).getPreviousWin(), equalTo(1997));
        assertThat(dto.getMax().get(0).getFollowingWin(), equalTo(2010));
        assertThat(dto.getMax().get(0).getInterval(), equalTo(13));
        assertThat(dto.getMax().get(0).getProducer(), equalTo("Kevin Costner"));

        assertThat(dto.getMax().get(1).getPreviousWin(), equalTo(2002));
        assertThat(dto.getMax().get(1).getFollowingWin(), equalTo(2015));
        assertThat(dto.getMax().get(1).getInterval(), equalTo(13));
        assertThat(dto.getMax().get(1).getProducer(), equalTo("Matthew Vaughn"));
    }
}
