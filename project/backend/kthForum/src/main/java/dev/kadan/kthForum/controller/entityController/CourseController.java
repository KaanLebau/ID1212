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


    public CourseDTO findByCourseId(String id){
        Course course = courseServices.findByCourseId(id);
        return  courseToCourseDTO(course);
    }


    public CourseDTO findbyId(Integer id){
        Course course= courseServices.findByDbId(id);
        return courseToCourseDTO(course);
    }



    public CourseDTO updateByCourseDbId(CourseDTO courseDTO){
        Course orgCourse = courseServices.findByDbId(courseDTO.id());
        orgCourse.setId(null);
        orgCourse.setCourseDesc(courseDTO.courseDesc());
        orgCourse.setCourseId(courseDTO.courseId());
        orgCourse.setCourseName(courseDTO.courseName());

        //orgCourse.setCourseRole(courseDTO.courseRoles().stream().map(Map));
        courseServices.removeByDbId(courseDTO.id());
        return creteCourse(courseDTO);
    }

    public CourseDTO updateByCourseId(@PathVariable String courseId, @RequestBody CourseDTO courseDTO){
        Course course = courseServices.findByCourseId(courseId);
        courseServices.removeByDbId(course.getId());
        Course theCourse;
        if(courseDTO.topicIdList().isEmpty()){
            theCourse = courseServices.createCourse(courseDTOToCourses(courseDTO));
        }else {
            List<Integer> topics  = courseDTO.topicIdList();
            courseDTO.topicIdList().removeAll(courseDTO.topicIdList());
            theCourse = courseServices.createCourse(courseDTOToCourses(courseDTO));

        }
        return courseToCourseDTO(theCourse);
    }
    /*
        Delete
     */

    public void removeByDbId(@PathVariable Integer dbId){
        courseServices.removeByDbId(dbId);
    }


    public void removeByCourseId(@PathVariable String courseId){
        Course course = courseServices.findByCourseId(courseId);
        courseServices.removeByDbId(course.getId());
    }


}
