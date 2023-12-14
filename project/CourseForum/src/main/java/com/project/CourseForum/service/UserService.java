package com.project.CourseForum.service;

import com.project.CourseForum.dto.UsersDTO;
import com.project.CourseForum.model.Users;


import java.util.List;

public interface UserService {
    List<UsersDTO> findAllUser();
    Users findByUsername(String username);
    UsersDTO findById(Integer id);
    Users saveUser(Users user);
}