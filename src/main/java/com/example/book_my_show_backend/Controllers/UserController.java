package com.example.book_my_show_backend.Controllers;


import com.example.book_my_show_backend.Dtos.UserRequestDto;
import com.example.book_my_show_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody() UserRequestDto user){
        userService.addUser(user);
        return "User added Successfully";
    }


    //Find user by name


    // Find all user



}
