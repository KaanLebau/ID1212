package com.example.lab5.repository;

import com.example.lab5.model.Results;
import com.example.lab5.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultsRepository extends JpaRepository <Results, Integer>{
    List<Results> getByUsers(Users users);
}
