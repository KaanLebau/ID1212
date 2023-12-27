package dev.kadan.kthForum.models;

import dev.kadan.kthForum.models.dto.CommentDTO;
import dev.kadan.kthForum.utilities.Mapper;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <b><i>ForumPost</i></b> object encapsulates the following information
 * <ul>
 *     <li> <b>id</b> type Integer database primary key</li>
 *     <li> <b>title</b> type String post title</li>
 *     <li><b>content</b> type String post content</li>
 *     <li><b>created</b> type LocalTime creation date</li>
 *     <li> <b>updated</b> type LocalDate update date</li>
 *      <br>
 *     <li> <b>topic</b> type {@link Topic} reference to topic which post belongs to</li>
 *     <li> <b>user</b> type {@link UserEntity} reference to the user who created the post</li>
 *     <li> <b>commentList</b> type <code>List<{@link CommentDTO}></code> list of comments on this post</li>
 * </ul>
 *
 * Objects contain getters, setters and constructors. There are no custom methods in this object.
 * @author Kaan Özsam
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class ForumPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name= "content" , length = 7000)
    private String content;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "updated")
    private LocalDate updated;

    @ManyToOne
    @JoinColumn(name = "topicId", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public ForumPost( String title, Topic topic, String content,UserEntity user, LocalDate created,LocalDate updated, List<Comment> commentList) {
        this.title = title;
        this.topic = topic;
        this.content = content;
        this.user = user;
        this.created = created;
        this.updated = updated;
        this.commentList = commentList;
    }
}
