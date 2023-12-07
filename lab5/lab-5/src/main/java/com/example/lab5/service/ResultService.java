package com.example.lab5.service;

import com.example.lab5.dto.ResultsDTO;
import com.example.lab5.model.Users;

import java.util.List;

public interface ResultService {

    List<ResultsDTO> getUserResult(Users users);
}
