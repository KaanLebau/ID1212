package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Topic;
import dev.kadan.kthForum.repositories.TopicRepository;
import dev.kadan.kthForum.services.TopicServeces;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TopicServecesImpl implements TopicServeces {
    private TopicRepository topicRepository;


    public TopicServecesImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @Override
    public List<Topic> getByCourseId(Integer id) {
        return topicRepository.findByCoursesId(id);
    }

    @Override
    public Topic getByDbId(Integer topicId) {
        return topicRepository.findById(topicId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Topic> getByTopicName(String topicName) {
        return topicRepository.findByTopicName(topicName);
    }

    @Override
    public void createTopic(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void removeById(Integer id) {
        topicRepository.deleteById(id);
    }

}

