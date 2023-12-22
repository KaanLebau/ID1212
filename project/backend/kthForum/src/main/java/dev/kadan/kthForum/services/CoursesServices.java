package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.Courses;
import dev.kadan.kthForum.models.dto.CourseDTO;

import java.util.List;

public interface CoursesServices {
    List<Courses> findAll();
    Courses findByCourseId(String id);
    Courses findByCourseName(String name);
    Courses findByDbId(Integer id);
    Courses createCourse(Courses course);
    void removeByDbId(Integer id);
    void removeByCourseId(String courseId);
}
