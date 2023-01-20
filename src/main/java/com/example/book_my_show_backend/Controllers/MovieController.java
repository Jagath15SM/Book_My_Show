package com.example.book_my_show_backend.Controllers;

import com.example.book_my_show_backend.Dtos.MovieRequestDto;
import com.example.book_my_show_backend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/get-movie-theaters/{movieName}")
    public List<String> getMovieTheaters(@PathVariable("movieName") String movieName){
        return movieService.getMovieTheaters(movieName);
    }

}
