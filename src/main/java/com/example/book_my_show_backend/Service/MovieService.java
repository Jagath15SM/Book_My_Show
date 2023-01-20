package com.example.book_my_show_backend.Service;

import com.example.book_my_show_backend.Dtos.MovieRequestDto;
import com.example.book_my_show_backend.Models.MovieEntity;
import com.example.book_my_show_backend.Models.ShowEntity;
import com.example.book_my_show_backend.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<String> getMovieTheaters(String movieName){
        // Get all Theater name where movie is played
        Set<String> result = new HashSet<>();
        MovieEntity movie = movieRepository.getMovieByName(movieName);
        List<ShowEntity> showEntityList = movie.getListOfShows();

        for(ShowEntity s : showEntityList){
            result.add(s.getTheater().getName());
        }

        return new ArrayList<>(result);
    }

}
