package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.repositories.CoursesRepository;
import dev.kadan.kthForum.services.CoursesServices;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CourseServicesImpl implements CoursesServices {
    private CoursesRepository coursesRepository;

    public CourseServicesImpl(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @Override
    public List<Course> findAll() {
        return coursesRepository.findAll();

    }

    @Override
    public Course findByCourseId(String id) {
        return coursesRepository.findByCourseId(id);

    }

    @Override
    public Course findByCourseName(String name) {
        return coursesRepository.findByCourseId(name);
    }

    @Override
    public Course findByDbId(Integer id) {
        return coursesRepository.findById(id).orElseThrow();
    }

    @Override
    public Course createCourse(Course course) {
        return coursesRepository.save(course);
    }

    @Override
    public void removeByDbId(Integer id) {
        coursesRepository.deleteById(id);
    }

    @Override
    public void removeByCourseId(String courseId) {
        coursesRepository.deleteByCourseId(courseId);
    }
}
