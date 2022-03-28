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
    void shouldRetornAwardDTOWithOneMinIntervalOneAndOneMaxIntervalThirteen() {
        AwardsDTO dto = service.get();

        assertThat(dto, notNullValue());
        assertThat(dto.getMin(), notNullValue());
        assertThat(dto.getMin().size(), equalTo(1));
        assertThat(dto.getMin().get(0).getPreviousWin(), equalTo(1990));
        assertThat(dto.getMin().get(0).getFollowingWin(), equalTo(1991));
        assertThat(dto.getMin().get(0).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(0).getProducer(), equalTo("Joel Silver"));

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
        movieService.updateMovieProducer(16, 202, 349);

        AwardsDTO dto = service.get();

        assertThat(dto, notNullValue());
        assertThat(dto.getMin(), notNullValue());
        assertThat(dto.getMin().size(), equalTo(2));
        assertThat(dto.getMin().get(0).getPreviousWin(), equalTo(1980));
        assertThat(dto.getMin().get(0).getFollowingWin(), equalTo(1981));
        assertThat(dto.getMin().get(0).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(0).getProducer(), equalTo("Roger M. Rothstein"));

        assertThat(dto.getMin().get(1).getPreviousWin(), equalTo(1990));
        assertThat(dto.getMin().get(1).getFollowingWin(), equalTo(1991));
        assertThat(dto.getMin().get(1).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(1).getProducer(), equalTo("Joel Silver"));

        assertThat(dto.getMax(), notNullValue());
        assertThat(dto.getMax().size(), equalTo(1));
        assertThat(dto.getMax().get(0).getPreviousWin(), equalTo(1982));
        assertThat(dto.getMax().get(0).getFollowingWin(), equalTo(2019));
        assertThat(dto.getMax().get(0).getInterval(), equalTo(37));
        assertThat(dto.getMax().get(0).getProducer(), equalTo("Mitsuharu Ishii"));
    }

    @Test
    void shouldRetornAwardDTOWithTwoMinAndTwoMax() {
        movieService.updateMovieProducer(28, 1, 1);
        movieService.updateMovieProducer(28, 11, 11);
        movieService.updateMovieProducer(72, 156, 95);

        AwardsDTO dto = service.get();

        assertThat(dto, notNullValue());
        assertThat(dto.getMin(), notNullValue());
        assertThat(dto.getMin().size(), equalTo(2));
        assertThat(dto.getMin().get(0).getPreviousWin(), equalTo(1980));
        assertThat(dto.getMin().get(0).getFollowingWin(), equalTo(1981));
        assertThat(dto.getMin().get(0).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(0).getProducer(), equalTo("Bo Derek"));

        assertThat(dto.getMin().get(1).getPreviousWin(), equalTo(1990));
        assertThat(dto.getMin().get(1).getFollowingWin(), equalTo(1991));
        assertThat(dto.getMin().get(1).getInterval(), equalTo(1));
        assertThat(dto.getMin().get(1).getProducer(), equalTo("Joel Silver"));

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
