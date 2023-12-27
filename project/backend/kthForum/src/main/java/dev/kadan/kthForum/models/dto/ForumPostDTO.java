package dev.kadan.kthForum.models.dto;


import dev.kadan.kthForum.models.ForumPost;


import java.time.LocalDate;
import java.util.List;

/**
 * This record is used to transport the <code>ForunPost</code> object in different layers of the application.
 * The record contains a simplified version of the {@link ForumPost} object.
 * @param id database identification, type Integer
 * @param title Post title, type String
 * @param content post content, type String
 * @param topicId used for Topic identification, type Integer
 * @param userId used for User identification, type Integer
 * @param created creation time stamp, type LocalTime
 * @param updated update time stamp, type LocalTime
 * @param commentList list of Comments in this topic, type List<{@link CommentDTO}>
 */
public record ForumPostDTO(
        Integer id,
        String title,
        String content,
       Integer topicId,
       Integer userId,
       LocalDate created,
        LocalDate updated,
        List<CommentDTO> commentList

) {
}
