package com.example.lab5.repository;

import com.example.lab5.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions, Integer> {
}
