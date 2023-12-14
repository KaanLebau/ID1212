package com.project.CourseForum.service.impl;


import com.project.CourseForum.dto.UsersDTO;

import com.project.CourseForum.model.Users;
import com.project.CourseForum.repository.UserRepository;
import com.project.CourseForum.service.UserService;
import com.project.CourseForum.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.CourseForum.util.Mapper.mapToUsersDTO;

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
