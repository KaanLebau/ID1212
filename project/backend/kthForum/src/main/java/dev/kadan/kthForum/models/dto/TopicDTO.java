package dev.kadan.kthForum.models.dto;



import java.util.List;

public record TopicDTO(
        Integer id,
        String topicName,
        Integer courseId,
        List<ForumPostDTO> postList
) {
}
