package dev.kadan.kthForum.controller.entityController;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.Topic;
import dev.kadan.kthForum.models.dto.TopicDTO;
import dev.kadan.kthForum.services.impl.CourseServicesImpl;
import dev.kadan.kthForum.services.impl.ForumPostServicesImpl;
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
    private final ForumPostServicesImpl postServices;

    public TopicController(TopicServecesImpl topicServeces, CourseServicesImpl courseServices, ForumPostServicesImpl postServices) {
        this.topicServeces = topicServeces;
        this.courseServices = courseServices;
        this.postServices = postServices;
    }

    /*
    Create
     */

    public TopicDTO createTopic(Topic topic){
        return topicToTopicDTO(topicServeces.createTopic(topic));
    }

    public List<TopicDTO> getAllTopics(){
        List<Topic> topicList = topicServeces.getAll();
        return topicList.stream().map(Mapper::topicToTopicDTO).collect(Collectors.toList());
    }


    public List<TopicDTO> getTopicByTitle(@PathVariable String topicName){
        return topicServeces.getByTopicName(topicName).stream().map(Mapper::topicToTopicDTO).collect(Collectors.toList());
    }

    public List<TopicDTO> getTopicByAuthor(@PathVariable Integer courseId){
        return topicServeces.getByCourseId(courseId).stream().map(Mapper::topicToTopicDTO).collect(Collectors.toList());
    }
    public TopicDTO findByDbId(Integer topicId){
        return topicToTopicDTO(topicServeces.getByDbId(topicId));
    }

    public TopicDTO updateByTopicDTO(Integer topicId, TopicDTO topicDTO){
        Topic org = topicServeces.getByDbId(topicId);
        org.setTopicName(topicDTO.topicName());
        org.setCourses(courseServices.findByDbId(topicDTO.courseId()));
        org.setPostList(postServices.findListOfPosts(topicDTO.postIdList()));
        topicServeces.removeById(topicDTO.id());
        return topicToTopicDTO(topicServeces.createTopic(org));
    }



    public void deleteByTopicDbId(Integer topicId){
        topicServeces.removeById(topicId);
    }

    public List<Topic> getListOfTopic(List<Integer> topicList) {
        return topicServeces.findListOfTopics(topicList);
    }
}
