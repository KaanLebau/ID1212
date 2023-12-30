package dev.kadan.kthForum.repositories;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.CourseUserRoles;
import dev.kadan.kthForum.models.Role;
import dev.kadan.kthForum.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRoleRepository extends JpaRepository<CourseUserRoles, Integer> {

    List<CourseUserRoles> findByCourse(Course course);
    List<CourseUserRoles> findByUser(UserEntity user);
    List<CourseUserRoles> findByRole(Role role);

    CourseUserRoles findByCourseAndUser(Course course, UserEntity user);

    void deleteById(Integer dbId);
}
