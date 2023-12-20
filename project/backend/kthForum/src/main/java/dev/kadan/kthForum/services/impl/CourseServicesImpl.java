package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Courses;
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
    public List<Courses> findAll() {
        return coursesRepository.findAll();

    }

    @Override
    public Courses findByCourseId(String id) {
        return coursesRepository.findByCourseId(id);

    }

    @Override
    public Courses findByCourseName(String name) {
        return coursesRepository.findByCourseId(name);
    }

    @Override
    public Courses findById(Integer id) {
        return coursesRepository.findById(id).orElseThrow();
    }
}
