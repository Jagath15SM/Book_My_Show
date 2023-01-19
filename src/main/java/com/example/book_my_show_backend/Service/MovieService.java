package com.example.book_my_show_backend.Service;

import com.example.book_my_show_backend.Dtos.MovieRequestDto;
import com.example.book_my_show_backend.Models.MovieEntity;
import com.example.book_my_show_backend.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


    public String addMovie(MovieRequestDto movieRequestDto){

        MovieEntity movie = MovieEntity.builder().
                name(movieRequestDto.getName()).
                duration(movieRequestDto.getDuration()).
                releaseDate(movieRequestDto.getReleaseDate()).
                build();
        try {
            movieRepository.save(movie);
        }catch (Exception e){
            System.out.println("Unable to add Movie");
        }

        return "Successfully added the movie";
    }

}
