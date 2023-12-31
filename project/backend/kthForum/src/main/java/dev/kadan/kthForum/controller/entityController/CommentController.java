package dev.kadan.kthForum.controller.entityController;

import dev.kadan.kthForum.models.Comment;
import dev.kadan.kthForum.models.ForumPost;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.models.dto.CommentDTO;
import dev.kadan.kthForum.services.ForumPostServices;
import dev.kadan.kthForum.services.impl.CommentServiceImpl;
import dev.kadan.kthForum.services.impl.UserServiceImpl;
import dev.kadan.kthForum.utilities.Mapper;
import jakarta.security.auth.message.AuthException;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static dev.kadan.kthForum.utilities.Mapper.commentToCommentDTO;

/**
 *
 * @Author Kaan Özsan
 */
@Controller
public class CommentController {
    private final CommentServiceImpl commentService;
    private final UserServiceImpl userService;

    private final ForumPostServices postServices;
    private Comment comment;

    public CommentController(CommentServiceImpl commentService, UserServiceImpl userService, ForumPostServices postServices) {
        this.commentService = commentService;
        this.userService = userService;
        this.postServices = postServices;
    }

    /**
     *
     * @param comment
     * @return
     */
    public CommentDTO createComment(Comment comment){
        return commentToCommentDTO(commentService.createComment(comment));
    }

    /*
    READ
     */
    public List<CommentDTO> getAllComments(){
        List<Comment> commentList = commentService.findAll();
        return commentList.stream().map(Mapper::commentToCommentDTO).collect(Collectors.toList());
    }

    /**
     *  getbyDbId() returns Comment identified by primary key
     * @param commentId db Primary key
     * @return {@link CommentDTO}
     */
    public CommentDTO getByDbId(Integer commentId){
        return commentToCommentDTO(commentService.getByDbId(commentId));
    }

    /**
     * Updates comment, found with comment id in database
     * received JSON object structure see below
     *
     *         <p>{</p>
     *             <p><b>"comment"</b>:<i>"This key contains String value"</i>,</p>
     *         <p>}</p>
     * updates comment in the database and
     * returns a {@link CommentDTO}object
     *
     *
     * @param commentDTO
     * @param commentId
     * @return
     */

    /**
     *
     * @param commentId
     * @param commentDTO
     * @return
     */
    public CommentDTO updateByComment(Integer commentId, CommentDTO commentDTO) {
        Comment updated = commentService.getByDbId(commentId);
        updated.setComment(commentDTO.comment());
        updated.setUpdated(LocalDate.now());
        return commentToCommentDTO(commentService.updateByComment(updated));
    }

    /**
     * This function removes a comment from the database.
     * This operation can be performed by only the comment author.
     * During unauthenticated attempts AuthException is thrown.
     * @param commentId
     * @param userId
     * @throws AuthException
     */
    public void deleteCommentById(Integer commentId, Integer userId) throws AuthException {
        Comment comment = commentService.getByDbId(commentId);
        if (comment.getUser().getId() != userId){
            throw new AuthException();
        }else{
            commentService.removeCommentById(commentId);
        }
    }

    public List<Comment> getlistOfComments(List<Integer> commentList) {
        return commentService.findListOfComments(commentList);
    }


}
