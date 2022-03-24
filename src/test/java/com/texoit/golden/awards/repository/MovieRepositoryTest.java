package com.texoit.golden.awards.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.texoit.golden.awards.model.Movie;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

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
}
