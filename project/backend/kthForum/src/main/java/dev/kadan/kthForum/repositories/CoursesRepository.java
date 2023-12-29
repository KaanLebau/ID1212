package dev.kadan.kthForum.repositories;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.dto.CourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Course, Integer> {
    Course findByCourseName(String name);
    Course findByCourseId(String id);

    void deleteById(Integer id);
    void deleteByCourseId(String courseId);

}
