package dev.kadan.kthForum.models;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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
