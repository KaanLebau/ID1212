package com.example.lab5.repository;

import com.example.lab5.model.Selector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SelectorRepository extends JpaRepository<Selector, Integer> {
    @Query("SELECT s FROM Selector s WHERE s.quiz_id = :quizId")
    List<Selector> findByQuizId(@Param("quizId") Integer quizId);
}
