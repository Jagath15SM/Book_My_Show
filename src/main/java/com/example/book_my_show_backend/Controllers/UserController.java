package com.example.book_my_show_backend.Controllers;


import com.example.book_my_show_backend.Dtos.UserRequestDto;
import com.example.book_my_show_backend.Dtos.UserResponseDto;
import com.example.book_my_show_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/get-user-by-name")
    public UserResponseDto getUser(@PathVariable String userName){
        return userService.getUser(userName);
    }

    // Find all user



}
