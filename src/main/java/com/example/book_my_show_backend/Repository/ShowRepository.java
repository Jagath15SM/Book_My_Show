package com.example.book_my_show_backend.Repository;

import com.example.book_my_show_backend.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {

}