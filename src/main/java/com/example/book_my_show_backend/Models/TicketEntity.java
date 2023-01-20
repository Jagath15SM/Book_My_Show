package com.example.book_my_show_backend.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "Tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String allotted_seats;

    private int amount; // total amount for Ticket

    private Date booked_at; // When ticket bookedAt


    @ManyToOne
    @JoinColumn
    private UserEntity user; //  Booked Ticket User

    @ManyToOne
    @JoinColumn
    private ShowEntity show; // Which show ticket booked

    // TicketEntity (Parent) -> ShowSeatEntity : 1 : M
    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> bookedSeats; // Booked seats

}

