package com.texoit.golden.awards.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwardsDTO {
    private List<ProducerAwardDTO> min;
    private List<ProducerAwardDTO> max;

    public AwardsDTO() {
        min = new ArrayList<>();
        max = new ArrayList<>();
    }
}
