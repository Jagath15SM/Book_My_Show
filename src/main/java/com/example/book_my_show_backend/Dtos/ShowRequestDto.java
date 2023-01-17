package com.example.book_my_show_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowRequestDto {

    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName;
    private int theaterId;
    private double multiplier;

    /* JSON
    {
        "showDate" : "2022-11-22",
        "showTime" : "22:00:00",
        "movieName" : "RRR",
        "theaterId" : 1,
        "multiplier": 2.5
    }
     */


}
