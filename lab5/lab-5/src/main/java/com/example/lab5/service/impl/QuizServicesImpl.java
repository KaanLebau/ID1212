package com.example.lab5.service.impl;

import com.example.lab5.dto.QuizDTO;
import com.example.lab5.model.Quiz;
import com.example.lab5.repository.QuizRepository;
import com.example.lab5.service.QuizServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class QuizServicesImpl implements QuizServices {
    QuizRepository quizRepository;

    public QuizServicesImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<QuizDTO> findAllQuizes() {
        List<Quiz> quizes =  quizRepository.findAll();
        return quizes.stream().map(this::mapToQuizDTO).collect(Collectors.toList());
    }

    private QuizDTO mapToQuizDTO(Quiz quiz) {
        return new QuizDTO(quiz.getId(), quiz.getSubject());
    }
    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
