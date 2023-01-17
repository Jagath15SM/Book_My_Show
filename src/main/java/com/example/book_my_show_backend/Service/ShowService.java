package com.example.book_my_show_backend.Service;

import com.example.book_my_show_backend.Dtos.ShowRequestDto;
import com.example.book_my_show_backend.Models.*;
import com.example.book_my_show_backend.Repository.MovieRepository;
import com.example.book_my_show_backend.Repository.ShowRepository;
import com.example.book_my_show_backend.Repository.ShowSeatRepository;
import com.example.book_my_show_backend.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;


    public String addShow(ShowRequestDto showRequestDto){

        // 1. Create ShowEntity
        ShowEntity showEntity = ShowEntity.builder()
                .showDate(showRequestDto.getShowDate())
                .showTime(showRequestDto.getShowTime())
                .multiplier(showRequestDto.getMultiplier())
                .build();

        // 2. We need to set foreign key : movieRepository & theaterRepository
        MovieEntity movieEntity = movieRepository.getMovieByName(showRequestDto.getMovieName());

        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        showEntity.setMovie(movieEntity);
        showEntity.setTheater(theaterEntity);

        // 3. Need to set ShowSeatEntity with ShowEntity
         List<ShowSeatEntity> seatEntityList = createTheaterSeatEntity(theaterEntity.getTheaterSeatEntityList());

         showEntity.setListOfSeats(seatEntityList);

         // For each Seat we need to assign Show
         for(ShowSeatEntity s : seatEntityList){
             s.setShow(showEntity);
         }
         showRepository.save(showEntity);
         return "Show added Successfully";
    }

    private List<ShowSeatEntity> createTheaterSeatEntity(List<TheaterSeatEntity> theaterSeatEntityList) {
        List<ShowSeatEntity> seats = new ArrayList<>();
        for(TheaterSeatEntity seat : theaterSeatEntityList){
            ShowSeatEntity showSeat = ShowSeatEntity.builder()
                    .seatNo(seat.getSeatNo())
                    .seatType(seat.getSeatType())
                    .build();
            seats.add(showSeat);
        }
        showSeatRepository.saveAll(seats);

        return seats;
    }


}
