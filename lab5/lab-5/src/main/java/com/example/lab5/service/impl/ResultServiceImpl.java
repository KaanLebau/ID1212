package com.example.lab5.service.impl;

import com.example.lab5.dto.ResultsDTO;
import com.example.lab5.model.Results;
import com.example.lab5.model.Users;
import com.example.lab5.repository.ResultsRepository;
import com.example.lab5.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

    private ResultsRepository resultsRepository;

    public ResultServiceImpl(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    private ResultsDTO mapToResultsDTO(Results results){
        return new ResultsDTO(results.getUsers().getId(), results.getQuizzes().getId(), results.getScore());}



    @Override
    public List<ResultsDTO> getUserResult(Users users) {
        List<Results> theResult = resultsRepository.getByUsers(users);
        return theResult.stream().map(this::mapToResultsDTO).collect(Collectors.toList());
    }

    @Override
    public Results saveResult(Results result) {
        return resultsRepository.save(result);
    }
}
