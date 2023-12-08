package com.example.lab5.service;

import com.example.lab5.dto.UsersDTO;
import com.example.lab5.model.Users;


import java.util.List;

public interface UserService {
    List<UsersDTO> findAllUser();
    Users findByUsername(String username);
    UsersDTO findById(Integer id);
    Users saveUser(Users user);
}
