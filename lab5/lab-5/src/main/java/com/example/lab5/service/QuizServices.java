package com.example.lab5.service;

import com.example.lab5.dto.QuizDTO;
import com.example.lab5.model.Quiz;


import java.util.List;

public interface QuizServices {
    List<QuizDTO> findAllQuizes();

    Quiz saveQuiz(Quiz quiz);
}
