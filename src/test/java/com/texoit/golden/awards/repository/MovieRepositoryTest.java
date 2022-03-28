package com.texoit.golden.awards.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.texoit.golden.awards.model.Movie;
import com.texoit.golden.awards.model.Producer;
import com.texoit.golden.awards.service.MovieService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private MovieService movieService;

    @BeforeEach
    void before() {
        movieService.resetDatabase();
    }

    @Test
    void shouldReturnAllMovies() {
        List<Movie> all = repository.findAll();

        assertThat(all.size(), equalTo(206));
    }

    @Test
    void shouldReturnFirstMovie() {
        Page<Movie> page = repository.findAll(PageRequest.of(0, 1));

        assertThat(page.getContent().get(0).getTitle(), equalTo("Can't Stop the Music"));
    }

    @Test
    void shouldReturnLastMovie() {
        Page<Movie> page = repository.findAll(PageRequest.of(205, 1));

        assertThat(page.getContent().get(0).getTitle(), equalTo("Rambo: Last Blood"));
    }

    @Test
    void shouldReturnWinnerMoviesByProducer() {
        Optional<Producer> op = producerRepository.findById(28);

        List<Movie> movies = repository.findAllByWinnerTrueAndProducersOrderByYearAsc(op.get());

        assertThat(movies.get(0).getTitle(), equalTo("Bolero"));
        assertThat(movies.get(0).getYear(), equalTo(1984));
        assertThat(movies.get(1).getTitle(), equalTo("Ghosts Can't Do It"));
        assertThat(movies.get(1).getYear(), equalTo(1990));
    }

}
