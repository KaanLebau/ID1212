package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Comment;
import dev.kadan.kthForum.models.dto.CommentDTO;
import dev.kadan.kthForum.repositories.CommentRepository;
import dev.kadan.kthForum.repositories.UserRepository;
import dev.kadan.kthForum.services.CommentServices;
import dev.kadan.kthForum.utilities.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    public List<Comment> getByUserId(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getCommentList();
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
    public List<Comment> findListOfComments(List<Integer> commentIdList) {
        List<Comment> commentList = new ArrayList<>();
        for(Integer id : commentIdList){
            commentList.add(commentRepository.findById(id).get());
        }
        return commentList;
    }

    @Override
    public void removeCommentById(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment updateByComment(Comment comment) {
        Comment updated = commentRepository.save(comment);
        return updated;
    }
}
