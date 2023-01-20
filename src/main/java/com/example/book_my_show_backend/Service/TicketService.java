package com.example.book_my_show_backend.Service;

import com.example.book_my_show_backend.Dtos.BookTicketRequestDto;
import com.example.book_my_show_backend.Dtos.TicketCancelledDto;
import com.example.book_my_show_backend.Dtos.TicketResponseDto;
import com.example.book_my_show_backend.Models.ShowEntity;
import com.example.book_my_show_backend.Models.ShowSeatEntity;
import com.example.book_my_show_backend.Models.TicketEntity;
import com.example.book_my_show_backend.Models.UserEntity;
import com.example.book_my_show_backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private MovieRepository movieRepository;


    public String bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto) throws Exception {

        List<String> requestedSeats = bookTicketRequestDto.getRequestedSeats();

        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowID()).get();

        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        // Get the seats from ShowSeatEntity
        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();


        List<ShowSeatEntity> bookedSeats = new ArrayList<>();

        // Checking availability of requested seats
        for(ShowSeatEntity s : showSeats){
            for(String seat : requestedSeats){
                String seatNo = s.getSeatNo();
                if(seatNo.equals(seat) && s.isBooked() == false){
                    bookedSeats.add(s);
                }
            }
        }

        // FAILURE
        if(bookedSeats.size() != requestedSeats.size()){
            // Some or All seats are not available to book
            throw new Exception("Requested seats are not available");
        }

        double totalAmount = 0;
        double multiplier = showEntity.getMultiplier();
        int rate = 100; // default for CLASSIC type
        String allottedSeats = "";

        // SUCCESS
        // Now here all requested seats are available, so need calculate amount
        TicketEntity ticketEntity = new TicketEntity();
        for(ShowSeatEntity bookedSeat : bookedSeats){
            // ShowSeatEntity setting
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);

            String seatNo = bookedSeat.getSeatNo();

            if(bookedSeat == bookedSeats.get(bookedSeats.size()-1)){
                allottedSeats += seatNo;
            }
            else{
                allottedSeats += seatNo + ",";
            }

            if(seatNo.startsWith("2")){
                rate = 200;
            }

            totalAmount += (multiplier * rate);
        }

        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int)totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAllotted_seats(allottedSeats);

        ticketRepository.save(ticketEntity);

        return "Generated Ticket successfully";
    }


    public List<TicketResponseDto> getUserTickets(int userId){
        List<TicketResponseDto> result = new ArrayList<>();
        UserEntity user = userRepository.findById(userId).get();

        for(TicketEntity t : user.getListOfTickets()){
            String theaterName = t.getShow().getTheater().getName();
            String movieName = t.getShow().getMovie().getName();

            TicketResponseDto ticket = TicketResponseDto.builder().showDate(t.getShow().getShowDate())
                    .showTime(t.getShow().getShowTime()).amount(t.getAmount())
                    .allottedSeats(t.getAllotted_seats()).movieName(movieName)
                    .theaterName(theaterName).build();
            result.add(ticket);
        }

        return result;
    }


    public TicketCancelledDto cancelTicket(int ticketId){
        // Cancelling ticket would do : refund 80%, delete from TicketEntity, ShowSeat entity
        TicketEntity ticket = ticketRepository.findById(ticketId).get();
        int refundAmount = (int) (ticket.getAmount() * 0.8);

        List<ShowSeatEntity> showSeatEntities = ticket.getBookedSeats();
        for(ShowSeatEntity s : showSeatEntities){
            s.setBooked(false);
            s.setBookedAt(null);
            s.setTicket(null);
        }

        // Removing corresponding Ticket in user Entity & Show entity
        UserEntity user = ticket.getUser();
        List<TicketEntity> movieTickets = user.getListOfTickets();
        movieTickets.remove(ticket); // We know ticket present.

        List<TicketEntity> showTickets = ticket.getShow().getListOfTickets();
        showTickets.remove(ticket);

        ticketRepository.delete(ticket);

        TicketCancelledDto result = TicketCancelledDto.builder()
                .ticketStatus("Ticket Cancelled Successfully")
                .refundedAmount(refundAmount).build();

        return result;
    }


}
