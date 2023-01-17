package com.example.book_my_show_backend.Service;


import com.example.book_my_show_backend.Dtos.TheaterRequestDto;
import com.example.book_my_show_backend.Enums.SeatType;
import com.example.book_my_show_backend.Models.TheaterEntity;
import com.example.book_my_show_backend.Models.TheaterSeatEntity;
import com.example.book_my_show_backend.Repository.TheaterRepository;
import com.example.book_my_show_backend.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    // Assume : all theater have 10 seats,
    // 5 classic : 1a, 1b, 1c, 1d, 1e
    // 5 platinum : 2a, 2b, 2c, 2d, 2e
    // we need to set TheaterSeat entity also
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        TheaterEntity theater = TheaterEntity.builder().
                name(theaterRequestDto.getName()).
                city(theaterRequestDto.getCity()).
                address(theaterRequestDto.getAddress()).
                build();

        // Need to set TheaterSeat list in Theater
        List<TheaterSeatEntity>  theaterSeatEntityList = createTheaterSeats();
        theater.setTheaterSeatEntityList(theaterSeatEntityList);
        // For each seat we need to set Theater
        for(TheaterSeatEntity theaterSeat : theaterSeatEntityList){
            theaterSeat.setTheater(theater);
        }

        theaterRepository.save(theater);

        return "Theater added successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats() {

        List<TheaterSeatEntity> seats = new ArrayList<>(10);

        for(int i=0;i<5;i++){
            seats.add(new TheaterSeatEntity("1" + (char)('A' + i), SeatType.CLASSIC, 100));
            seats.add(new TheaterSeatEntity("2" + (char)('A' + i), SeatType.PLATINUM, 200));
        }

        theaterSeatRepository.saveAll(seats);

        return seats;
    }

}
