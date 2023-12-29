package dev.kadan.kthForum.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.kadan.kthForum.models.dto.TopicDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><i>Course</i></b> object encapsulates the following information
 * <ul>
 *     <li> <b>id</b> type Integer database primary key</li>
 *     <li> <b>courseId</b> type String business kode for Course</li>
 *     <li><b>courseName</b> type String course name</li>
 *     <li> <b>courseDesc</b> type String Course description</li>
 *     <br>
 *     <li> <b>topicList</b> type <code>List<{@link Topic}></code> list of topics registered in this course</li>
 *     <li> <b>courseRole</b> type <code>List<{@link CourseUserRoles}></code> list of users and their role in a course</li>
 * </ul>
 * Objects contain getters, setters and constructors. There are no custom methods in this object.
 * @author Kaan Özsam
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
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
    @JsonManagedReference
    private List<Topic> topicList = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<CourseUserRoles> courseRole = new ArrayList<>();
}



