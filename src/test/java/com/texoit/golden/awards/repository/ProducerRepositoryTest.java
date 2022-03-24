package com.texoit.golden.awards.repository;

import static org.hamcrest.CoreMatchers.*;
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

import com.texoit.golden.awards.model.Producer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class ProducerRepositoryTest {

    @Autowired
    private ProducerRepository repository;

    @Test
    void shouldReturnAllProducers() {
        List<Producer> all = repository.findAll();

        assertThat(all.size(), equalTo(300));
    }

    @Test
    void shouldReturnFirstProducer() {
        Page<Producer> page = repository.findAll(PageRequest.of(0, 1));

        assertThat(page.getContent().get(0).getName(), equalTo("Allan Carr"));
    }

    @Test
    void shouldReturnLastProducer() {
        Page<Producer> page = repository.findAll(PageRequest.of(299, 1));

        assertThat(page.getContent().get(0).getName(), equalTo("and Les Weldon"));
    }

    @Test
    void shouldReturnProducerByName() {
        Producer producer = repository.findByName("Neil Canton");

        assertThat(producer, notNullValue());
    }

    @Test
    void shouldReturnProducerWithMoreThanOneAward() {
        List<Producer> producers = repository.findProducersWithMoreThanOneAward();

        assertThat(producers.get(0).getId(), equalTo(26));
        assertThat(producers.get(1).getId(), equalTo(119));
    }
}
