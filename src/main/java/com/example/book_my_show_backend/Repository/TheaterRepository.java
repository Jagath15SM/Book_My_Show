package com.example.book_my_show_backend.Repository;

import com.example.book_my_show_backend.Models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
}
