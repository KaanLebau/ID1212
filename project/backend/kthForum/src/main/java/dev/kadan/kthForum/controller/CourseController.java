package dev.kadan.kthForum.controller;

import dev.kadan.kthForum.models.Courses;
import dev.kadan.kthForum.models.dto.CourseDTO;
import dev.kadan.kthForum.models.dto.TopicDTO;
import dev.kadan.kthForum.services.impl.CourseServicesImpl;
import dev.kadan.kthForum.services.impl.TopicServecesImpl;
import dev.kadan.kthForum.utilities.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static dev.kadan.kthForum.utilities.Mapper.*;

@RestController
public class CourseController {
    private final CourseServicesImpl courseServices;
    private final TopicServecesImpl topicServeces;

    private final String BASE_PATH = "/api/v1/courses/";

    public CourseController(CourseServicesImpl courseServices, TopicServecesImpl topicServeces) {
        this.courseServices = courseServices;
        this.topicServeces = topicServeces;
    }

    /*
        CREATE
     */
    @PostMapping(BASE_PATH + "new")
    public CourseDTO creteCourse(@RequestBody CourseDTO courseDTO){
        Courses theCourse;
        if(courseDTO.topicList().isEmpty()){
            theCourse = courseServices.createCourse(courseDTOToCourses(courseDTO));
        }else {
            List<TopicDTO> topics  = courseDTO.topicList();
            courseDTO.topicList().removeAll(courseDTO.topicList());
            theCourse = courseServices.createCourse(courseDTOToCourses(courseDTO));
            for (TopicDTO topic : topics){
                topicServeces.createTopic(topicDTOToTopic(topic));
            }
        }
        return courseToCourseDTO(theCourse);
    }

    /*
            READ
     */
    @GetMapping(BASE_PATH)
    public List<CourseDTO> findAllCourses(){
        List<Courses> courseList = courseServices.findAll();
        List<CourseDTO> response = courseList.stream().map(Mapper::courseToCourseDTO).collect(Collectors.toList());
        return response;
    }

    @GetMapping(BASE_PATH + "courseid/{id}")
    public CourseDTO findByCourseId(@PathVariable String id){
        Courses course = courseServices.findByCourseId(id);
        return  courseToCourseDTO(course);
    }

    @GetMapping(BASE_PATH + "coursedbid/{id}")
    public CourseDTO findbyId(@PathVariable Integer id){
        Courses course= courseServices.findByDbId(id);
        return courseToCourseDTO(course);
    }

    /*
    Update
     */
    @PutMapping(BASE_PATH + "update/coursedbid/{dbId}")
    public CourseDTO updateByCourseDbId(@PathVariable Integer dbId, @RequestBody CourseDTO courseDTO){
        courseServices.removeByDbId(dbId);
        return creteCourse(courseDTO);
    }
    @PutMapping(BASE_PATH + "update/courseid/{courseId}")
    public CourseDTO updateByCourseId(@PathVariable String courseId, @RequestBody CourseDTO courseDTO){
        Courses course = courseServices.findByCourseId(courseId);
        courseServices.removeByDbId(course.getId());
        Courses theCourse;
        if(courseDTO.topicList().isEmpty()){
            theCourse = courseServices.createCourse(courseDTOToCourses(courseDTO));
        }else {
            List<TopicDTO> topics  = courseDTO.topicList();
            courseDTO.topicList().removeAll(courseDTO.topicList());
            theCourse = courseServices.createCourse(courseDTOToCourses(courseDTO));
            for (TopicDTO topic : topics){
                topicServeces.createTopic(topicDTOToTopic(topic));
            }
        }
        return courseToCourseDTO(theCourse);
    }
    /*
        Delete
     */
    @DeleteMapping("/api/v1/courses/removedb/{dbId}")
    public void removeByDbId(@PathVariable Integer dbId){
        courseServices.removeByDbId(dbId);
    }

    @DeleteMapping(BASE_PATH + "removecourse/{courseId}")
    public void removeByCourseId(@PathVariable String courseId){
        Courses course = courseServices.findByCourseId(courseId);
        courseServices.removeByDbId(course.getId());
    }


}
