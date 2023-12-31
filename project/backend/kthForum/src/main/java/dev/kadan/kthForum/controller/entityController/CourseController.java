package dev.kadan.kthForum.controller.entityController;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.dto.CourseDTO;
import dev.kadan.kthForum.models.dto.TopicDTO;
import dev.kadan.kthForum.services.impl.CourseServicesImpl;
import dev.kadan.kthForum.services.impl.TopicServecesImpl;
import dev.kadan.kthForum.utilities.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static dev.kadan.kthForum.utilities.Mapper.*;

@Controller
public class CourseController {
    private final CourseServicesImpl courseServices;
    private final TopicServecesImpl topicServeces;


    public CourseController(CourseServicesImpl courseServices, TopicServecesImpl topicServeces) {
        this.courseServices = courseServices;
        this.topicServeces = topicServeces;
    }

    public CourseDTO creteCourse(CourseDTO courseDTO){
        Course theCourse = courseServices.createCourse(courseDTOToCourses(courseDTO));
        return courseToCourseDTO(theCourse);
    }



    public List<CourseDTO> findAllCourses(){
        List<Course> courseList = courseServices.findAll();
        List<CourseDTO> response = courseList.stream().map(Mapper::courseToCourseDTO).collect(Collectors.toList());
        return response;
    }


    public Course findByCourseId(String id){
        return courseServices.findByCourseId(id);
    }


    public Course findbyDbId(Integer id){
        return courseServices.findByDbId(id);
    }



    public CourseDTO updateByCourse(Integer courseId, CourseDTO courseDTO){
        Course orgCourse = courseServices.findByDbId(courseId);
        orgCourse.setCourseDesc(courseDTO.courseDesc());
        orgCourse.setCourseId(courseDTO.courseId());
        orgCourse.setCourseName(courseDTO.courseName());
        return courseToCourseDTO(courseServices.updateByDbId(courseId, orgCourse));
    }


    /*
        Delete
     */

    public void deleteByCourseDbId(Integer dbId){
        courseServices.removeByDbId(dbId);
    }


    public void removeByCourseId(@PathVariable String courseId){
        Course course = courseServices.findByCourseId(courseId);
        courseServices.removeByDbId(course.getId());
    }



}
