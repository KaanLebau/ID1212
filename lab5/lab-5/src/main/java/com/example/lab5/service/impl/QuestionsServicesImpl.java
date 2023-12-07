package com.example.lab5.service.impl;

import com.example.lab5.dto.QuestionsDTO;
import com.example.lab5.model.Questions;
import com.example.lab5.repository.QuestionRepository;
import com.example.lab5.service.QuestionsService;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class QuestionsServicesImpl implements QuestionsService {
    private QuestionRepository questionRepository;

    public QuestionsServicesImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionsDTO> getQuestionsByIds(List<Integer> idList) {
        List<Questions> questions = new ArrayList<>();
        for (Integer id : idList){
            questions.add(questionRepository.findById(id).get());
        }
        return questions.stream().map(this::mapToQuestionsDTO).collect(Collectors.toList());
    }

    private QuestionsDTO mapToQuestionsDTO(Questions questions){
        return new QuestionsDTO(questions.getText(), questions.getOptions(), questions.getAnswer());
    }
}
