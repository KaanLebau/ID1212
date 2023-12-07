package com.example.lab5.util;


import com.example.lab5.dto.UsersDTO;
import com.example.lab5.model.Users;

public class user {
    public static UsersDTO mapToUsersDTO(Users user){
        return new UsersDTO(user.getId(), user.getUsername());

    }
    public static Users mapToUsers(UsersDTO dto){
        return new Users(dto.id(), dto.username(), null);
    }
}
