package com.example.lab5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "results")
public class Results {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "id")
    Integer id;
    @Column(name = "score")
    Integer score;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users users;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    Quizzes quizzes;


}
