package com.example.book_my_show_backend.Repository;

import com.example.book_my_show_backend.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    MovieEntity getMovieByName(String movieName);
}
