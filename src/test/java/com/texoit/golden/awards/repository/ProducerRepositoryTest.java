package com.texoit.golden.awards.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.texoit.golden.awards.model.Producer;
import com.texoit.golden.awards.service.MovieService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProducerRepositoryTest {

    @Autowired
    private ProducerRepository repository;

    @Autowired
    private MovieService movieService;

    @BeforeEach
    void before() {
        movieService.resetDatabase();
    }

    @Test
    void shouldReturnAllProducers() {
        List<Producer> all = repository.findAll();

        assertThat(all.size(), equalTo(359));
    }

    @Test
    void shouldReturnFirstProducer() {
        Page<Producer> page = repository.findAll(PageRequest.of(0, 1));

        assertThat(page.getContent().get(0).getName(), equalTo("Allan Carr"));
    }

    @Test
    void shouldReturnLastProducer() {
        Page<Producer> page = repository.findAll(PageRequest.of(299, 1));

        assertThat(page.getContent().get(0).getName(), equalTo("Gregory Goodman"));
    }

    @Test
    void shouldReturnProducerByName() {
        Producer producer = repository.findByName("Neil Canton");

        assertThat(producer, notNullValue());
    }

    @Test
    void shouldReturnProducerWithMoreThanOneAward() {
        List<Producer> producers = repository.findProducersWithMoreThanOneAward();

        assertThat(producers.size(), equalTo(5));
        assertThat(producers.get(0).getId(), equalTo(16));
        assertThat(producers.get(1).getId(), equalTo(28));
        assertThat(producers.get(2).getId(), equalTo(32));
        assertThat(producers.get(3).getId(), equalTo(58));
        assertThat(producers.get(4).getId(), equalTo(152));
    }
}
