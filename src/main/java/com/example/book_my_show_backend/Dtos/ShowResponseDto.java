package com.example.book_my_show_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponseDto {

    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;

    private String theaterName;

}
