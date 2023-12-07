package com.example.lab5.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface SelectorService {

    List<Integer> getQuestionsIds(Integer quizId);
}
