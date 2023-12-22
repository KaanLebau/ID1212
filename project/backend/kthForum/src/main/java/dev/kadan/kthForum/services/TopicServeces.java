package dev.kadan.kthForum.services;


import dev.kadan.kthForum.models.Topic;
import dev.kadan.kthForum.models.dto.TopicDTO;

import java.util.List;

public interface TopicServeces {


    List<Topic> getAll();
    List<Topic> getByCourseId(Integer id);

    List<Topic> getByTopicName(String topicName);
    void  createTopic(Topic topic);

    void removeById(Integer id);

}
