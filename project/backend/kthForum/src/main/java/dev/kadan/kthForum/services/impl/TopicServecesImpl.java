package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Courses;
import dev.kadan.kthForum.models.Topic;
import dev.kadan.kthForum.models.dto.TopicDTO;
import dev.kadan.kthForum.repositories.CoursesRepository;
import dev.kadan.kthForum.repositories.TopicRepository;
import dev.kadan.kthForum.services.TopicServeces;
import dev.kadan.kthForum.utilities.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static dev.kadan.kthForum.utilities.Mapper.topicToTopicDTO;
@Service
public class TopicServecesImpl implements TopicServeces {
    private TopicRepository topicRepository;
    private CoursesRepository coursesRepository;

    public TopicServecesImpl(TopicRepository topicRepository, CoursesRepository coursesRepository) {
        this.topicRepository = topicRepository;
        this.coursesRepository = coursesRepository;
    }

    @Override
    public List<TopicDTO> getByCourseId(Integer id) {
        List<Topic> topicList = topicRepository.findByCoursesId(id);
        return topicList.stream().map(Mapper::topicToTopicDTO).collect(Collectors.toList());
    }

    @Override
    public void createTopic(Integer id, TopicDTO topicDTO) {
        Courses course = coursesRepository.findById(id).get();
    }

}

