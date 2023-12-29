package dev.kadan.kthForum.models.dto;

/**
 * This record is used to transport the <code>CourseUserRoles</code> object in different layers of the application.
 *  * The record contains a simplified version of the <i>{@link CourseUserRolesDTO}</i> object.
 *
 * @param id database identification, type Integer
 * @param userId used for User identification, type Integer
 * @param roleId used for Role identification, type Integer
 * @param courseId used for Course identification, type Integer
 */
public record CourseUserRolesDTO(
        Integer id,
        Integer userId,
        Integer roleId,
        Integer courseId
) {
}
