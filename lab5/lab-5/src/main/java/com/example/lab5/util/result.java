package com.example.lab5.util;

import com.example.lab5.dto.ResultsDTO;
import com.example.lab5.model.Results;

public class result {

    public static ResultsDTO mapToResultsDTO(Results theResult){
        return new ResultsDTO(theResult.getUsers().getId(), theResult.getId(), theResult.getScore());
    }
}
