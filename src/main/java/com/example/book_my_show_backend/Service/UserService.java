package com.example.book_my_show_backend.Service;

import com.example.book_my_show_backend.Dtos.TicketResponseDto;
import com.example.book_my_show_backend.Dtos.UserRequestDto;
import com.example.book_my_show_backend.Dtos.UserResponseDto;
import com.example.book_my_show_backend.Models.UserEntity;
import com.example.book_my_show_backend.Repository.TicketRepository;
import com.example.book_my_show_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketService ticketService;

    public String addUser(UserRequestDto user){

        UserEntity userEntity = UserEntity.builder().
                name(user.getName()).
                mobile(user.getMobile()).build();
        try {
            userRepository.save(userEntity);
        }
        catch (Exception e){
            return "User could not be added";
        }

        return "User added successfully";
    }

    public UserResponseDto getUser(@PathVariable String userName){
        UserEntity user = userRepository.findByName(userName);

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .name(user.getName()).mobile(user.getMobile()).build();

        List<TicketResponseDto> tickets = ticketService.getUserTickets(user.getId());

        userResponseDto.setListOfTickets(tickets);
        return userResponseDto;
    }

}
