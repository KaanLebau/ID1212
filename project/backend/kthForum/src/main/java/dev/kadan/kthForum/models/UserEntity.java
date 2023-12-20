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
                joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
        )
        private List<Role> roleList = new ArrayList<>();

        @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
        private List<ForumPost> postList = new ArrayList<>();

        public UserEntity(Object o, String username, Object o1) {
                this.username = username;
        }
}
