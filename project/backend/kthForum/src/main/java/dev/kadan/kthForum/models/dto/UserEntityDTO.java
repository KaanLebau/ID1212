package dev.kadan.kthForum.models.dto;


import dev.kadan.kthForum.models.*;

import java.util.List;

/**
 * This record is used to transport the <code>UserEntity</code> object in different layers of the application.
 *  * The record contains a simplified version of the {@link UserEntity} object.
 * @param id database identification, type Integer
 * @param username username type String
 * @param displayName display name type String
 * @param email users email type String
 * @param postList list of posts created by user type <code>List<{@link ForumPostDTO}></code>
 * @param commentList list of comments created by user type <code>List<{@link CommentDTO}></code>
 * @param courseRoles list of users and their role in a course, type List<{@link CourseUserRoles}>
 */
public record UserEntityDTO(
        Integer id,
        String username,
        String displayName,
        String email,
        List<Integer> postIdList,
        List<Integer> commentIdList,
        List<CourseUserRoles> courseRoles
) {
}
