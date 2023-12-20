package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.Courses;

import java.util.List;

public interface CoursesServices {
    List<Courses> findAll();
    Courses findByCourseId(String id);
    Courses findByCourseName(String name);
    Courses findById(Integer id);
}
