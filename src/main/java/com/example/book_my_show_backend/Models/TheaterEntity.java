package com.example.book_my_show_backend.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "Theater")
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    private String city;

    private String address;

    //List of shows : TheaterEntity (Parent) -> ShowEntity : 1 : M
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    List<ShowEntity> listOfShows;


    //List of theater seats : TheaterEntity (Parent) -> TheaterSeatEntity : 1 : M
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<TheaterSeatEntity> theaterSeatEntityList;

}
