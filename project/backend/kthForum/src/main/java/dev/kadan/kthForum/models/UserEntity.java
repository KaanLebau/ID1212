package dev.kadan.kthForum.models;

import dev.kadan.kthForum.models.dto.CommentDTO;
import dev.kadan.kthForum.models.dto.ForumPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><i>UserEntity</i></b> object encapsulates the following information
 * <ul>
 *     <li> <b>id</b> type Integer database primary key</li>
 *     <li> <b>username</b> type String user name</li>
 *     <li><b>displayName</b> type String display name in application</li>
 *     <br>
 *     <li> <b>roleList</b> type <i>{@link Role}</i> user role</li>
 *     <li> <b>postList</b> type <code>List<{@link ForumPostDTO}></code> list of all posts of the user</li>
 *     <li> <b>commentList</b> type <code>List<{@link CommentDTO}></code> list of all comments of the user</li>
 * </ul>
 * Objects contain getters, setters and constructors. There are no custom methods in this object.
 * @author Kaan Özsam
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;
        @Column(name = "username",length = 30)
        private String username;
        @Column(name = "displayName", length = 20)
        private String displayName;

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(
                name = "user_roles",
                joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
        )
        private List<Role> roleList = new ArrayList<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
        private List<ForumPostDTO> postList = new ArrayList<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
        private List<CommentDTO> commentList = new ArrayList<>();

        public UserEntity(Object o, String username, Object o1) {
                this.username = username;
        }
}
