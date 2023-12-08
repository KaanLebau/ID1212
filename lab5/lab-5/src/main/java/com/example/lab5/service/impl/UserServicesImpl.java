package com.example.lab5.service.impl;


import com.example.lab5.dto.UsersDTO;

import com.example.lab5.model.Users;
import com.example.lab5.repository.UserRepository;
import com.example.lab5.service.UserService;
import com.example.lab5.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.lab5.util.Mapper.mapToUsers;
import static com.example.lab5.util.Mapper.mapToUsersDTO;

@Service
public class UserServicesImpl implements UserService {
    private UserRepository userRepository;
    private Users loggedIn;


    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UsersDTO> findAllUser() {
        List<Users> users = userRepository.findAll();

        return users.stream().map(Mapper::mapToUsersDTO).collect(Collectors.toList());
    }
    public UsersDTO getLoggedIn(){
        return mapToUsersDTO(this.loggedIn);
    }


    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
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
