package dev.kadan.kthForum.models.dto;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.CourseUserRoles;

import java.util.List;

/**
 * This record is used to transport the <code>Course</code> object in different layers of the application.
 * The record contains a simplified version of the <i>{@link Course}</i> object.
 * @param id database identification, type Integer
 * @param courseId business kode for Course , type String
 * @param courseName Course name, type String
 * @param courseDesc Course description, type String
 * @param topicIdList list of topic ids in this course, type List<Integer>
 * @param courseRoles list of users and their role in a course, type List<{@link CourseUserRoles}>
 */
public record CourseDTO(
        Integer id,
        String courseId,
        String courseName,
        String courseDesc,
        List<Integer> topicIdList,
        List<CourseUserRolesDTO> courseRoles


) {
}
