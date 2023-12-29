package dev.kadan.kthForum.models.dto;



import dev.kadan.kthForum.models.ForumPost;
import dev.kadan.kthForum.models.Topic;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * This object is used to transport the <code>Topic</code> object in different layers of the application.
 * The object contains a simplified version of the <i> {@link Topic} </i> object
 * @param id database identification, type Integer
 * @param topicName current topic name,  type String
 * @param courseId used for post identification, type Integer
 * @param postIdList list of post id in this topic, type List<Integer>
 *
 * @author Kaan Özsan
 */

public record TopicDTO(
        Integer id,
        String topicName,
        Integer courseId,
        List<Integer> postIdList
) {
}
