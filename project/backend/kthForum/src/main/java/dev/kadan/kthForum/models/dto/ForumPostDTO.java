package dev.kadan.kthForum.models.dto;

import dev.kadan.kthForum.models.Comment;
import dev.kadan.kthForum.models.Topic;
import dev.kadan.kthForum.models.UserEntity;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.List;

public record ForumPostDTO(

        String title,
        String content,
       Integer topic_id,
       Integer user_id,
       LocalDate created,
        LocalDate updated,
        List<Comment> commentList

) {
}
