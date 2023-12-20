package dev.kadan.kthForum.models.dto;

import java.util.List;

public record CourseDTO(
        Integer id,
        String courseId,
        String courseName,
        String courseDesc,
        List<TopicDTO> topicList

) {
}
