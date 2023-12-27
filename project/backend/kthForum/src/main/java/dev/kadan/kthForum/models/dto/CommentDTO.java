package dev.kadan.kthForum.models.dto;

import dev.kadan.kthForum.models.Comment;


import java.time.LocalDate;

/**
 * This object is used to transport the <code>Comment</code> object in different layers of the application.
 * The object contains a simplified version of the <i>{@link Comment}</i> object.
 * @param id database identification, type Integer
 * @param comment Comment content, type String
 * @param postId used for post identification, type Integer
 * @param userId used for user identification, type Integer
 * @param created time stamp, type LocalTime
 * @param updated time stamp, type LocalTime
 *
 * @author Kaan Özsan
 */
public record CommentDTO(
        Integer id,
        String comment,
        Integer postId,
        Integer userId,
        LocalDate created,
        LocalDate updated
) {
}
