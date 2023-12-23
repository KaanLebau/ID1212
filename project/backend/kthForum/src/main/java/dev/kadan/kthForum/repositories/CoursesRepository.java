package dev.kadan.kthForum.repositories;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.dto.CourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Course, Integer> {
    Course findByCourseName(String name);
    Course findByCourseId(String id);

    Course save(CourseDTO course);
    void deleteById(Integer id);
    void deleteByCourseId(String courseId);

}
