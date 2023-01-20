package com.example.book_my_show_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {

    private String movieName;

    private String theaterName;

    private LocalDate showDate;

    private LocalTime showTime;

    private int amount;

    private String allottedSeats;

}
