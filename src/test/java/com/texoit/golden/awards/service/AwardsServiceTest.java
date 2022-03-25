package com.texoit.golden.awards.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

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

    @Test
    void shouldRetornAwardDTOWithMinAndMaxValues() {
        AwardsDTO dto = service.get();

        assertThat(dto, notNullValue());
        assertThat(dto.getMin(), notNullValue());
        assertThat(dto.getMin().get(0).getInterval(), equalTo(6));
        assertThat(dto.getMin().get(0).getProducer(), equalTo("Bo Derek"));

        assertThat(dto.getMax(), notNullValue());
        assertThat(dto.getMax().get(0).getInterval(), equalTo(13));
        assertThat(dto.getMax().get(0).getProducer(), equalTo("Matthew Vaughn"));
    }
}
