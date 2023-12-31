package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.Comment;
import dev.kadan.kthForum.models.dto.CommentDTO;

import java.util.List;

public interface CommentServices {
    List<Comment> findAll();

    List<Comment> getByUserId(Integer userId);
    Comment getByDbId(Integer commentId);

    Comment createComment(Comment comment);
    List<Comment> findListOfComments(List<Integer> commentIdList);
    void removeCommentById(Integer commentId);
    Comment updateByComment(Comment comment);
}
