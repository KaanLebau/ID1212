package dev.kadan.kthForum.controller.frontendController;

import dev.kadan.kthForum.controller.entityController.*;
import dev.kadan.kthForum.models.dto.CommentDTO;
import dev.kadan.kthForum.models.dto.ForumPostDTO;
import dev.kadan.kthForum.models.dto.KthUser;
import dev.kadan.kthForum.models.dto.UserEntityDTO;
import jakarta.security.auth.message.AuthException;
import org.springframework.web.bind.annotation.*;
@RestController
public class FrontendController {
    private final CourseController courseController;
    private final ForumPostController postController;
    private final TopicController topicController;
    private final UserController userController;
    private final CommentController commentController;

    public FrontendController(CourseController courseController, ForumPostController postController, TopicController topicController, UserController userController, CommentController commentController) {
        this.courseController = courseController;
        this.postController = postController;
        this.topicController = topicController;
        this.userController = userController;
        this.commentController = commentController;
    }

    @PostMapping("api/v1/login")
    public UserEntityDTO userLogin(@RequestBody KthUser kthUser){
        return userController.login(kthUser);
    }

    @GetMapping("api/v1/user/{userId}")
    public UserEntityDTO getUserData(@PathVariable Integer userId){
        return userController.getUserById(userId);
    }

    @PostMapping("/api/v1/user/{userId}/course/{courseId}/topic/{topicId}/post/new")
    public ForumPostDTO createPost(@RequestBody ForumPostDTO forumPostDTO, @PathVariable Integer userId){
        return null;
    }
    @PostMapping("/api/v1/user/{userId}/course/{courseId}/topic/{topicId}/post/{postId}/comment/new")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer userId, @PathVariable Integer postId){
        return commentController.createComment(commentDTO, userId, postId);
    }
    @PostMapping("/api/v1/user/{userId}/course/{courseId}/topic/{topicId}/post/{postId}/comment/update/{commentId}")
    public CommentDTO updateCommentById(@RequestBody CommentDTO commentDTO, @PathVariable Integer commentId, @PathVariable Integer userId){
        try {
            return commentController.updateComment(commentDTO, commentId, userId);
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/api/v1/user/{userId}/course/{courseId}/topic/{topicId}/post/{postId}/comment/delete/{commentId}")
    public void deleteCommentById(@PathVariable Integer commentId, @PathVariable Integer userId){
        try {
            commentController.deleteCommentById(commentId, userId);
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }
}
