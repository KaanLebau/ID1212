package dev.kadan.kthForum.models.dto;


import dev.kadan.kthForum.models.Role;
import dev.kadan.kthForum.models.UserEntity;

import java.util.List;

/**
 * This record is used to transport the <code>UserEntity</code> object in different layers of the application.
 *  * The record contains a simplified version of the {@link UserEntity} object.
 * @param id database identification, type Integer
 * @param username username type String
 * @param displayName display name type String
 * @param roleList list of roles connected to user type <code>List<{@link Role}></code>
 * @param postList list of posts created by user type <code>List<{@link ForumPostDTO}></code>
 * @param commentList list of comments created by user type <code>List<{@link CommentDTO}></code>
 */
public record UserEntityDTO(
        Integer id,
        String username,
        String displayName,
        List<Role> roleList,
        List<ForumPostDTO> postList,
        List<CommentDTO> commentList
) {
}
