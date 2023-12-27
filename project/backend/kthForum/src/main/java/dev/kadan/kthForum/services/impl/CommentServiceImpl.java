package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Comment;
import dev.kadan.kthForum.repositories.CommentRepository;
import dev.kadan.kthForum.repositories.UserRepository;
import dev.kadan.kthForum.services.CommentServices;

import java.util.List;

public class CommentServiceImpl implements CommentServices {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getByUserId(Integer userId) {
        return userRepository.findById(userId).get().getCommentList();
    }

    @Override
    public Comment getByDbId(Integer commentId) {
        return commentRepository.findById(commentId).get();
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void removeCommentById(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
}
