package com.example.book_my_show_backend.Dtos;

import lombok.Data;

import java.util.Date;

@Data
public class MovieRequestDto {

    private String name;
    private int duration;
    private Date releaseDate;
}
