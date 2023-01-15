package com.example.book_my_show_backend.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "Movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    private int duration;

    private Date releaseDate;

    // MovieEntity (Parent) -> ShowEntity : 1 : M
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ShowEntity> listOfShows;
}

