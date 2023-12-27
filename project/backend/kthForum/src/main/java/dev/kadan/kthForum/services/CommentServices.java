package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.Comment;
import dev.kadan.kthForum.models.dto.CommentDTO;

import java.util.List;

public interface CommentServices {
    List<Comment> findAll();

    List<CommentDTO> getByUserId(Integer userId);
    Comment getByDbId(Integer commentId);

    Comment createComment(Comment comment);

    void removeCommentById(Integer commentId);
}
