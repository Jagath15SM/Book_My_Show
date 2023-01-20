package com.example.book_my_show_backend.Controllers;

import com.example.book_my_show_backend.Dtos.GetAllShowsByMovieRequestDto;
import com.example.book_my_show_backend.Dtos.ShowRequestDto;
import com.example.book_my_show_backend.Dtos.ShowResponseDto;
import com.example.book_my_show_backend.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public String addShow(@RequestBody ShowRequestDto showRequestDto){
        return showService.addShow(showRequestDto);
    }


    @GetMapping("/get-all-shows/by-movie")
    public List<ShowResponseDto> getAllShows(@RequestBody GetAllShowsByMovieRequestDto getAllShowsByMovie){
        return showService.getAllShows(getAllShowsByMovie);
    }

}
