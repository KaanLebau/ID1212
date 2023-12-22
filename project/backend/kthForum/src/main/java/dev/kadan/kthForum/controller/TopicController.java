package dev.kadan.kthForum.controller;

import dev.kadan.kthForum.models.Courses;
import dev.kadan.kthForum.models.ForumPost;
import dev.kadan.kthForum.models.Topic;
import dev.kadan.kthForum.models.dto.TopicDTO;
import dev.kadan.kthForum.services.impl.CourseServicesImpl;
import dev.kadan.kthForum.services.impl.TopicServecesImpl;
import dev.kadan.kthForum.utilities.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static dev.kadan.kthForum.utilities.Mapper.topicDTOToTopic;
import static dev.kadan.kthForum.utilities.Mapper.topicToTopicDTO;

@RestController
public class TopicController {
    private final TopicServecesImpl topicServeces;
    private final CourseServicesImpl courseServices;
    private final String BASE_PATH = "/api/v1/topics/";

    public TopicController(TopicServecesImpl topicServeces, CourseServicesImpl courseServices) {
        this.topicServeces = topicServeces;
        this.courseServices = courseServices;
    }

    /*
    Create
     */
    @PostMapping(BASE_PATH + "new")
    public void createTopic(@RequestBody TopicDTO topicDTO){
        Topic theTopic = topicDTOToTopic(topicDTO);
        Courses course = courseServices.findByDbId(topicDTO.courseId());
        theTopic.setCourses(course);

        topicServeces.createTopic(topicDTOToTopic(topicDTO));
    }
    /*
    Read
     */
    @GetMapping(BASE_PATH)
    public List<TopicDTO> getAllTopics(){
        List<Topic> topicList = topicServeces.getAll();
        return topicList.stream().map(Mapper::topicToTopicDTO).collect(Collectors.toList());
    }

    @GetMapping(BASE_PATH + "topicbyname/{topicName}")
    public List<TopicDTO> getTopicByTitle(@PathVariable String topicName){
        return topicServeces.getByTopicName(topicName).stream().map(Mapper::topicToTopicDTO).collect(Collectors.toList());
    }
    @GetMapping(BASE_PATH + "topicbycourse/{courseId}")
    public List<TopicDTO> getTopicByAuthor(@PathVariable Integer courseId){
        return topicServeces.getByCourseId(courseId).stream().map(Mapper::topicToTopicDTO).collect(Collectors.toList());
    }
    /*
    Update
     */
    @PutMapping(BASE_PATH + "update/topicbyid/{id}")
    public void updateByTopicID(@PathVariable Integer topicId, @RequestBody TopicDTO topicDTO){
        topicServeces.removeById(topicId);
        createTopic(topicDTO);
    }

    /*
    Delete
     */
    @DeleteMapping(BASE_PATH + "remove/byid/{id}")
    public void removeTopicById(@PathVariable Integer topicId){
        topicServeces.removeById(topicId);
    }
}
