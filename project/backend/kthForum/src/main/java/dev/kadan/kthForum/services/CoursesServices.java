package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.Course;

import java.util.List;

public interface CoursesServices {
    List<Course> findAll();
    Course findByCourseId(String id);
    Course findByCourseName(String name);
    Course findByDbId(Integer id);
    Course createCourse(Course course);
    void removeByDbId(Integer id);
    void removeByCourseId(String courseId);
    List<Course> findListOfCourses(List<Integer> courseIdList);
}
