package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.CourseUserRoles;
import dev.kadan.kthForum.models.Role;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.models.dto.CourseUserRolesDTO;

import java.util.List;

public interface CourseRoleServices {

    CourseUserRoles createCourseRole(CourseUserRoles courseUserRoles);

    List<CourseUserRoles> findByCourse(Course course);
    List<CourseUserRoles> findByUser(UserEntity user);
    List<CourseUserRoles> findByRole(Role role);
    CourseUserRoles findByDbId(Integer dbId);

    CourseUserRoles findByCourseAndUser(Course course, UserEntity user);

    void removeCourseRole(Integer  courseUserRolesId);

}
