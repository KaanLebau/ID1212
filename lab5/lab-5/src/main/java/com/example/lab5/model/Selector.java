package com.example.lab5.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "selector")
public class Selector {
    @Id
    @Column(name = "quiz_id")
    Integer id;
    @Column(name = "question_id")
    Integer question_id;

    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    private Set<Question> question = new HashSet<>();
}
