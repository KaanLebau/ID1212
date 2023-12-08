package com.example.lab5.util;

import com.example.lab5.dto.QuizzesDTO;
import com.example.lab5.dto.ResultsDTO;
import com.example.lab5.dto.UsersDTO;
import com.example.lab5.model.Quizzes;
import com.example.lab5.model.Results;
import com.example.lab5.model.Users;

public class Mapper {
    public static Quizzes mapToQuizes(QuizzesDTO quizzesDTO){ return new Quizzes(quizzesDTO.id(), quizzesDTO.subject());}
    public static UsersDTO mapToUsersDTO(Users user){return new UsersDTO(user.getId(), user.getUsername());}
    public static Users mapToUsers(UsersDTO dto){
        return new Users(dto.id(), dto.username(), null);
    }
    public static ResultsDTO mapToResultsDTO(Results theResult){
        return new ResultsDTO(theResult.getUsers().getId(), theResult.getUsers().getUsername(), theResult.getId(), theResult.getScore());
    }
}
