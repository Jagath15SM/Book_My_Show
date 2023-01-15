package com.example.book_my_show_backend.Controllers;

import com.example.book_my_show_backend.Dtos.MovieRequestDto;
import com.example.book_my_show_backend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody() MovieRequestDto movieRequestDto){
        movieService.addMovie(movieRequestDto);
        return "Movie added Successfully";
    }
}
