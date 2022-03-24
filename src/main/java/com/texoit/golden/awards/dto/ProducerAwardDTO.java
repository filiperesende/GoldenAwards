package com.texoit.golden.awards.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducerAwardDTO {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
