package com.example.book_my_show_backend.Controllers;


import com.example.book_my_show_backend.Dtos.TheaterRequestDto;
import com.example.book_my_show_backend.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;


    // 1. Add theater
    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        String result = theaterService.addTheater(theaterRequestDto);
        return result;
    }

    // 2. Get Theater by id


    // 3. Get all Theaters

    
}
