package dev.kadan.kthForum.repositories;

import dev.kadan.kthForum.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    Courses findByCourseName(String name);
    Courses findByCourseId(String id);

}
