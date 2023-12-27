package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Comment;
import dev.kadan.kthForum.models.dto.CommentDTO;
import dev.kadan.kthForum.repositories.CommentRepository;
import dev.kadan.kthForum.repositories.UserRepository;
import dev.kadan.kthForum.services.CommentServices;
import dev.kadan.kthForum.utilities.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
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
    public List<CommentDTO> getByUserId(Integer userId) {
        return userRepository.findById(userId).get().getCommentList().stream().map(Mapper::commentToCommentDTO).collect(Collectors.toList());
    }

    @Override
    public Comment getByDbId(Integer commentId) throws NoSuchElementException {
        return commentRepository.findById(commentId).orElseThrow(NoSuchElementException::new);
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
