package com.example.lab5.service;

import com.example.lab5.dto.QuestionsDTO;
import com.example.lab5.model.Questions;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionsService {
    List<QuestionsDTO> getQuestionsByIds(List<Integer> idList);
}
