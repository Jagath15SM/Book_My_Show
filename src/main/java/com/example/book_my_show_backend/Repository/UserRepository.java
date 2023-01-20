package com.example.book_my_show_backend.Repository;

import com.example.book_my_show_backend.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByName(String name);
}
