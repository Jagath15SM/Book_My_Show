package com.example.book_my_show_backend.Controllers;

import com.example.book_my_show_backend.Dtos.BookTicketRequestDto;
import com.example.book_my_show_backend.Dtos.TicketCancelledDto;
import com.example.book_my_show_backend.Dtos.TicketResponseDto;
import com.example.book_my_show_backend.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public String bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto){
        try{
            return  ticketService.bookTicket(bookTicketRequestDto);
        }catch (Exception e){
            return "Requested seats not available to book";
        }
    }

    @GetMapping("/get-all-tickets-booked-by-user/{userId}")
    public List<TicketResponseDto> getUserTickets(@PathVariable("userId") int userId){
        return ticketService.getUserTickets(userId);
    }

    @PutMapping("/cancel-ticket/{ticketId}")
    public TicketCancelledDto cancelTicket(@PathVariable("ticketId") int ticketId){
        return ticketService.cancelTicket(ticketId);
    }


}
