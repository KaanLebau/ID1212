package dev.kadan.kthForum.controller;

import dev.kadan.kthForum.models.Courses;
import dev.kadan.kthForum.models.dto.CourseDTO;
import dev.kadan.kthForum.services.impl.CourseServicesImpl;
import dev.kadan.kthForum.utilities.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static dev.kadan.kthForum.utilities.Mapper.courseToCourseDTO;

@RestController
public class CourseController {
    private final CourseServicesImpl courseServices;

    public CourseController(CourseServicesImpl courseServices) {
        this.courseServices = courseServices;
    }

    @GetMapping("/api/v1/courses")
    public List<CourseDTO> findAllCourses(){
        List<Courses> courseList = courseServices.findAll();
        List<CourseDTO> response = courseList.stream().map(Mapper::courseToCourseDTO).collect(Collectors.toList());
        return response;
    }

    @GetMapping("/api/v1/courses/courseid/{id}")
    public CourseDTO findByCourseId(@PathVariable String id){
        Courses course = courseServices.findByCourseId(id);
        return  courseToCourseDTO(course);
    }

    @GetMapping("/api/v1/courses/course/{id}")
    public CourseDTO findbyId(@PathVariable Integer id){
        Courses course= courseServices.findById(id);
        return courseToCourseDTO(course);
    }
}
