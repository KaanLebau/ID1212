package dev.kadan.kthForum.services;


import dev.kadan.kthForum.models.dto.TopicDTO;

import java.util.List;

public interface TopicServeces {
    List<TopicDTO> getByCourseId(Integer id);

    void  createTopic(Integer courseId, TopicDTO topicDTO);

}
