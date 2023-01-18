package com.example.book_my_show_backend.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequestDto {
    private int showID;
    private int userId;
    private List<String> requestedSeats;
}
