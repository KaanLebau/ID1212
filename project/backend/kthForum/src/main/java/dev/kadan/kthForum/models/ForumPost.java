package dev.kadan.kthForum.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

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

    @Column(name = "authorId")
    private Integer authoId;

    @Column(name="authorName", length = 30)
    private String authorName;

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
    private UserEntity userEntity;

    public ForumPost(Integer authoId, String authorName, String title, String content) {
        this.authoId = authoId;
        this.authorName = authorName;
        this.title = title;
        this.content = content;
        this.created = LocalDate.now();
        this.updated = null;
    }
}
