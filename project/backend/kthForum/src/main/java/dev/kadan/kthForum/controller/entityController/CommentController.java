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
     * Creates a comment on a post
     * receives JSON object, structure see below
     *
     *               <p>{</p>
     *                   <p><b>"comment"</b>:<i>"This key contains String value"</i>,</p>
     *               <p>}</p>
     *
     *  Sets created to LocalDate.now()<br>
     *  Sets user<br>
     *  Sets post<br>
     * returns a {@link CommentDTO}object
     *
     * @param commentDTO Object contains comment
     * @param userId    used for user identification, type Integer
     * @param postId    used for post identification, type Integer
     * @return CommentDTO
     */
    public CommentDTO createComment(CommentDTO commentDTO,Integer userId, Integer postId){
        UserEntity theUser = userService.getByDbId(userId);
        ForumPost thePost = postServices.getByDbId(postId);
        Comment newComment =commentService.createComment(new Comment(null, commentDTO.comment(),LocalDate.now(),null,thePost,theUser));
        return commentToCommentDTO(newComment);
    }

    /*
    READ
     */
    public List<CommentDTO> getAllComments(){
        List<Comment> commentList = commentService.findAll();
        return commentList.stream().map(Mapper::commentToCommentDTO).collect(Collectors.toList());
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
    public CommentDTO updateComment(CommentDTO commentDTO, Integer commentId, Integer userId) throws AuthException {
        comment = commentService.getByDbId(commentId);
        LocalDate created = comment.getCreated();
        ForumPost thePost = comment.getPosts();
        UserEntity theUser = comment.getUser();
        if(comment.getUser().getId() != userId){
            throw new AuthException();
        }else{
            comment = new Comment(null, comment.getComment(), created,LocalDate.now(),thePost,theUser);
            commentService.removeCommentById(commentId);
            commentService.createComment(comment);
            comment = null;
        }
        return null;
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

}
