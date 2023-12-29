package dev.kadan.kthForum.repositories;

import dev.kadan.kthForum.models.CourseUserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRoleRepository extends JpaRepository<CourseUserRoles, Integer> {

    List<CourseUserRoles> findByCoursesId(Integer courseId);
    List<CourseUserRoles> findByUserId(Integer userId);
    List<CourseUserRoles> findByRoleId(Integer roleId);
}
