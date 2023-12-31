package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.repositories.CoursesRepository;
import dev.kadan.kthForum.services.CoursesServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Course> findListOfCourses(List<Integer> courseIdList) {
        List<Course> courseList = new ArrayList<>();
        for(Integer id  : courseIdList){
            courseList.add(coursesRepository.findById(id).get());
        }
        return courseList;
    }

    @Override
    public Course updateByDbId(Integer courseId, Course course) {
        /*
        Course orgCourse = coursesRepository.findById(courseId).get();
        orgCourse.setId(null);
        orgCourse.setCourseDesc(course.getCourseDesc());
        orgCourse.setCourseId(course.getCourseId());
        orgCourse.setCourseName(course.getCourseName());
        */
        Course updated = coursesRepository.save(course);

        return updated;
    }
}
