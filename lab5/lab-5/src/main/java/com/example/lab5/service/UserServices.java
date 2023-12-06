package com.example.lab5.service;

import com.example.lab5.dto.UserDTO;
import com.example.lab5.model.User;


import java.util.List;

public interface UserServices {
    List<UserDTO> findAllUser();

    User saveUser( User user);
}
