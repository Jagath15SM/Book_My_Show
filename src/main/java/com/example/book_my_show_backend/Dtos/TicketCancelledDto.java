package com.example.book_my_show_backend.Dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketCancelledDto {
    private int refundedAmount;
    private String ticketStatus;
}
