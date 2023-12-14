package com.project.CourseForum.util;

import com.project.CourseForum.dto.UsersDTO;
import com.project.CourseForum.model.Users;

public class Mapper {
    public static UsersDTO mapToUsersDTO(Users user){return new UsersDTO(user.getId(), user.getUsername());}
    public static Users mapToUsers(UsersDTO dto){
        return new Users(dto.id(), dto.username(), null);
    }
}