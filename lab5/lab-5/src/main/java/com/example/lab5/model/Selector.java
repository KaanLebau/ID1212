package com.example.lab5.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="selector")
public class Selector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "quiz_id")
    Integer quiz_id;

    @Column(name = "question_id")
    Integer Question_id;

}
