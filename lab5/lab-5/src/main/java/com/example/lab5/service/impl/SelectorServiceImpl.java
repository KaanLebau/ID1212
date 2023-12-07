package com.example.lab5.service.impl;

import com.example.lab5.model.Selector;
import com.example.lab5.repository.SelectorRepository;
import com.example.lab5.service.SelectorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SelectorServiceImpl implements SelectorService {
    SelectorRepository selectorRepository;

    public SelectorServiceImpl(SelectorRepository selectorRepository) {
        this.selectorRepository = selectorRepository;
    }

    @Override
    public List<Integer> getQuestionsIds(Integer id) {
        List<Selector> selected = selectorRepository.findByQuizId(id);
        List<Integer> ids = new ArrayList<>();
        for(Selector s : selected){
            ids.add(s.getQuestion_id());
        }
        return ids;
    }
}
