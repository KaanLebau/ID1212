package dev.kadan.kthForum.services;


import dev.kadan.kthForum.models.Topic;
import dev.kadan.kthForum.models.dto.TopicDTO;

import java.util.List;

public interface TopicServeces {


    List<Topic> getAll();
    List<Topic> getByCourseId(Integer id);

    Topic getByDbId(Integer topicId);

    List<Topic> getByTopicName(String topicName);
    Topic createTopic(Topic topic);

    void removeById(Integer id);

    List<Topic> findListOfTopics(List<Integer> topipIdList);
    Topic updateByTopic(Topic topic);

}
