package com.example.lab5.service;

import com.example.lab5.dto.QuestionsDTO;
import com.example.lab5.dto.QuizzesDTO;
import com.example.lab5.model.Questions;
import com.example.lab5.model.Quizzes;


import java.util.List;

public interface QuizService {
    List<QuizzesDTO> findAllQuizzes();

    QuizzesDTO findById(Integer id);
    List<QuestionsDTO> getQuizQuestions(Integer id);
    Quizzes saveQuiz(Quizzes quizzes);

    QuizzesDTO getByQuizId(Integer id);
}
