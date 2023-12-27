package dev.kadan.kthForum.models.dto;

public record CommentDTO(
        String comment,
        Integer postId,
        Integer userId
) {
}
