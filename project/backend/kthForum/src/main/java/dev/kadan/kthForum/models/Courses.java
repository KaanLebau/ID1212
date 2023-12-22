package dev.kadan.kthForum.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "courseId", length = 6)
    private String courseId;
    @Column(name = "courseName",length = 50)
    private String courseName;
    @Column(name = "courseDesc", length = 1000)
    private String courseDesc;


    @OneToMany(mappedBy = "courses", cascade = CascadeType.REMOVE)
    private List<Topic> topicList = new ArrayList<>();
}



