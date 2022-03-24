package com.texoit.golden.awards.model;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer year;
    private String title;

    @ManyToMany
    @JoinTable(name = "movie_studio",
               joinColumns = { @JoinColumn(name = "movie_id")},
               inverseJoinColumns = { @JoinColumn(name = "studio_id") })
    private List<Studio> studios;

    @ManyToMany
    @JoinTable(name = "movie_producer",
               joinColumns = { @JoinColumn(name = "movie_id") },
               inverseJoinColumns = { @JoinColumn(name = "producer_id") })
    private List<Producer> producers;

    private boolean winner;
}
