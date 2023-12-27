package dev.kadan.kthForum.models;

import dev.kadan.kthForum.models.dto.UserEntityDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Comment object encapsulates the following information
 * <ul>
 *     <li> <b>id</b> type Integer database primary key</li>
 *     <li> <b>roleName</b> type String role name </li>
 *     <br>
 *     <li> <b>userList</b> type <code>List<{@link UserEntityDTO}></code> list of user with the role</li>
 *
 *     Objects contain getters, setters and constructors. There are no custom methods in this object.
 * </ul>
 * @author Kaan Özsam
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name", length = 8)
    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    private List<UserEntity> userList = new ArrayList<>();

}
