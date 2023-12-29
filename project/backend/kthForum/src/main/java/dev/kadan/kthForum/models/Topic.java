package dev.kadan.kthForum.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.kadan.kthForum.models.dto.ForumPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><i>Topic</i></b> object encapsulates the following information
 * <ul>
 *     <li> <b>id</b> type Integer database primary key</li>
 *     <li> <b>topicName</b> type String topic name</li>
 *     <br>
 *     <li><b>courses</b> type {@link Course} course object to which topic belongs </li>
 *     <br>
 *     <li> <b>postList</b> type <code>List<{@link ForumPostDTO}></code> list of all posts in that belong to that topic</li>
 * </ul>
 * Objects contain getters, setters and constructors. There are no custom methods in this object.
 * @author Kaan Özsam
 */
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
    @JsonBackReference
    private Course courses;
    @JsonBackReference
    public Course getCourses() {
        return courses;
    }

    @OneToMany(mappedBy = "topic", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<ForumPost> postList = new ArrayList<>();
    @JsonManagedReference
    public List<ForumPost> getPostList() {
        return postList;
    }
}
