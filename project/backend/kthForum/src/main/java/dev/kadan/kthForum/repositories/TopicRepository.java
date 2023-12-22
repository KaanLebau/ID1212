package dev.kadan.kthForum.repositories;

import dev.kadan.kthForum.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findByCoursesId(Integer id);

    List<Topic> findByTopicName(String topicName);


}
