package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.Comment;

import java.util.List;

public interface CommentServices {
    List<Comment> findAll();

    List<Comment> getByUserId(Integer userId);
    Comment getByDbId(Integer commentId);

    Comment createComment(Comment comment);

    void removeCommentById(Integer commentId);
}
