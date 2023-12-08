package com.example.lab5.service.impl;

import com.example.lab5.dto.QuestionsDTO;
import com.example.lab5.dto.QuizzesDTO;
import com.example.lab5.model.Questions;
import com.example.lab5.model.Quizzes;
import com.example.lab5.repository.QuestionRepository;
import com.example.lab5.repository.QuizRepository;
import com.example.lab5.service.QuestionsService;
import com.example.lab5.service.QuizService;
import com.example.lab5.service.SelectorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class QuizServicesImpl implements QuizService {
    QuizRepository quizRepository;
    QuestionsService questionsService;
    SelectorService selectorService;


    public QuizServicesImpl(QuizRepository quizRepository,
                            QuestionsService questionsService,
                            SelectorService selectorService) {
        this.quizRepository = quizRepository;
        this.questionsService = questionsService;
        this.selectorService = selectorService;
    }

    @Override
    public List<QuizzesDTO> findAllQuizzes() {
        List<Quizzes> quizzes =  quizRepository.findAll();
        return quizzes.stream().map(this::mapToQuizDTO).collect(Collectors.toList());
    }

    @Override
    public QuizzesDTO findById(Integer id) {
        Optional<Quizzes> quiz = quizRepository.findById(id);
        return null;
    }

    @Override
    public List<QuestionsDTO> getQuizQuestions(Integer id) {
        List<Integer> questionsId = selectorService.getQuestionsIds(id);
        return questionsService.getQuestionsByIds(questionsId);
    }


    private QuizzesDTO mapToQuizDTO(Quizzes quiz) {
        return new QuizzesDTO(quiz.getId(), quiz.getSubject());
    }
    @Override
    public Quizzes saveQuiz(Quizzes quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public QuizzesDTO getByQuizId(Integer id) {
        return mapToQuizDTO(quizRepository.getById(id));
    }
}
