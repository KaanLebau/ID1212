package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.CourseUserRoles;
import dev.kadan.kthForum.models.dto.CourseUserRolesDTO;

import java.util.List;

public interface CourseRoleServices {

    CourseUserRoles createCourseRole(CourseUserRoles courseUserRoles);

    List<CourseUserRoles> findByCourse(Integer courseId);
    List<CourseUserRoles> findByUser(Integer userId);
    List<CourseUserRoles> findByRole(Integer roleId);
    CourseUserRoles findByDbId(Integer dbId);

    void removeCourseRole(CourseUserRoles courseUserRoles);

}
