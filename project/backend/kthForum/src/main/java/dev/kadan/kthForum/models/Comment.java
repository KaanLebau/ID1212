package dev.kadan.kthForum.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b><i>Comment</i></b> object encapsulates the following information
 * <ul>
 *     <li> <b>id</b> type Integer database primary key</li>
 *     <li> <b>comment</b> type String comment content</li>
 *     <li><b>created</b> type LocalTime creation date</li>
 *     <li> <b>updated</b> type LocalDate update date</li>
 *     <br>
 *     <li> <b>post</b> {@link ForumPost} reference to which post was commented</li>
 *     <li> <b>user</b> {@link UserEntity} reference to the user who created the comment</li>
 * </ul>
 * Objects contain getters, setters and constructors. There are no custom methods in this object.
 * @author Kaan Özsam
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "comment",length = 1000)
    String comment;
    @Column(name = "created")
    private LocalDate created;

    @Column(name = "updated")
    private LocalDate updated;

    @ManyToOne
    @JoinColumn(name = "posts_id", nullable = false)
    private ForumPost posts;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;
}
