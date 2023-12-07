package com.example.lab5.service.impl;


import com.example.lab5.dto.UsersDTO;

import com.example.lab5.model.Users;
import com.example.lab5.repository.UserRepository;
import com.example.lab5.service.UserService;
import com.example.lab5.util.user;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServicesImpl implements UserService {
    private UserRepository userRepository;



    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UsersDTO> findAllUser() {
        List<Users> users = userRepository.findAll();

        return users.stream().map(user::mapToUsersDTO).collect(Collectors.toList());
    }


    public UsersDTO findByUsername(String username) {
        Users theUser =userRepository.findByUsername(username);


        return new UsersDTO(theUser.getId(), theUser.getUsername());
    }

    @Override
    public UsersDTO findById(Integer id) {
        Users theUser = userRepository.findById(id).get();

        return new UsersDTO(theUser.getId(), theUser.getUsername());
    }


    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }


}
