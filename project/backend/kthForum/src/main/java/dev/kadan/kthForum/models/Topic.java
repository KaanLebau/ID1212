package dev.kadan.kthForum.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "topic_name", length = 60)
    String topicName;


    @ManyToOne
    @JoinColumn(name = "courses_id", nullable = false)
    private Course courses;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.REMOVE)
    private List<ForumPost> postList = new ArrayList<>();



}
