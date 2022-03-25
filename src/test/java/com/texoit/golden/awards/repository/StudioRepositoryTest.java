package com.texoit.golden.awards.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.texoit.golden.awards.model.Studio;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StudioRepositoryTest {

    @Autowired
    private StudioRepository repository;

    @Test
    void shouldReturnAllStudios() {
        List<Studio> all = repository.findAll();

        assertThat(all.size(), equalTo(59));
    }

    @Test
    void shouldReturnFirstStudio() {
        Page<Studio> page = repository.findAll(PageRequest.of(0, 1));

        assertThat(page.getContent().get(0).getName(), equalTo("Associated Film Distribution"));
    }

    @Test
    void shouldReturnLastStudio() {
        Page<Studio> page = repository.findAll(PageRequest.of(58, 1));

        assertThat(page.getContent().get(0).getName(), equalTo("Saban Films"));
    }

    @Test
    void shouldReturnStudioByName() {
        Studio studio = repository.findByName("Cannon Films");

        assertThat(studio, notNullValue());
    }
}
