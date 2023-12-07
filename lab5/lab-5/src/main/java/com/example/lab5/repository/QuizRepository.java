package com.example.lab5.repository;

import com.example.lab5.model.Quizzes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quizzes, Integer> {

}
