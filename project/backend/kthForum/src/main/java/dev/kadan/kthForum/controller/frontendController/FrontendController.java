package dev.kadan.kthForum.controller.frontendController;

import dev.kadan.kthForum.controller.entityController.*;
import dev.kadan.kthForum.models.*;
import dev.kadan.kthForum.models.dto.*;
import jakarta.security.auth.message.AuthException;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static dev.kadan.kthForum.utilities.Mapper.*;

@RestController
public class FrontendController {
    private final CourseController courseController;
    private final ForumPostController postController;
    private final TopicController topicController;
    private final UserController userController;
    private final CommentController commentController;
    private final CourseRoleController courseRoleController;

    public FrontendController(CourseController courseController, ForumPostController postController, TopicController topicController, UserController userController, CommentController commentController, CourseRoleController courseRoleController) {
        this.courseController = courseController;
        this.postController = postController;
        this.topicController = topicController;
        this.userController = userController;
        this.commentController = commentController;
        this.courseRoleController = courseRoleController;
    }

    /**
     * <b>Description: </b> User login
     * <br>
     * <b>RequestBody: </b> should have the following structure
     * <pre>
     *     {
     *         <b>username: </b> "This key contains String value",
     *         <b>password: </b> "This key contains String value",
     *     }
     * </pre>
     *<br>
     *
     * @param kthUser see RequestBody
     * @return {@link UserEntityDTO} with all related data
     */
    @PostMapping("api/v1/login")
    public UserEntityDTO userLogin(@RequestBody KthUser kthUser){
        return userController.login(kthUser);
    }

    /**
     * <b>Description: </b> fetch a user with db id
     * <br>
     * <b>Authorization level: </b> login required for fetch.
     * <br>
     * <b>RequestBody: </b> path variable is used no request body is required
     *
     * @param userId user database primary key
     * @return {@link UserEntityDTO}
     */
    @GetMapping("api/v1/user/{userId}")//TODO is working
    public UserEntityDTO getUserData(@PathVariable Integer userId){
        return userController.getUserById(userId);
    }

    /**
     * <b>Description: </b> Creates a new {@link Course}
     * <br>
     * <b>Authorization level: </b> only Admin can create a course.
     * <br>
     * <b>RequestBody: </b> should have the following structure
     * <pre>
     *      {
     *          <b>courseId: </b> "This key contains String value",
     *          <b>courseName: </b> "This key contains String value",
     *          <b>courseDesc: </b> "This key contains String value",
     *      }
     * </pre>
      * @param courseDTO see RequestBody
     * @param userId used to determine the authorization level
     * @return {@link CourseDTO}
     */
    @PostMapping("api/v1/user/{userId}/course/new")//TODO working
    public CourseDTO creteCourse(@RequestBody CourseDTO courseDTO, @PathVariable Integer userId){

            return courseController.creteCourse(
                    new CourseDTO(
                            null,
                            courseDTO.courseId(),
                            courseDTO.courseName(),
                            courseDTO.courseDesc(),
                            null,
                            null)
            );

    }

    /**
     * <b>Description: </b> Creates a new {@link Topic}
     * <br>
     * <b>Authorization level: </b> only ADMIN or TEACHER can create a topic.
     * <br>
     * <b>RequestBody: </b> should have the following structure
     * <pre>
     *      {
     *          <b>topicName: </b> "This key contains String value",
     *
     *      }
     * </pre>
     * @param topicDTO see RequestBody
     * @param userId used to determine the authorization level
     * @param courseId course reference for assign topic
     * @return {@link TopicDTO}
     */
    @PostMapping("api/v1/user/{userId}/course/{courseId}/topic/new")
    public TopicDTO createTopic(@RequestBody TopicDTO topicDTO, @PathVariable Integer userId, @PathVariable Integer courseId){
        Topic newTopic = new Topic(
                null,
                topicDTO.topicName(),
                courseController.findbyDbId(courseId),
                null
        );
        return topicController.createTopic(newTopic);
    }

    /**
     * <b>Description: </b> Creates a new {@link ForumPost}
     * <br>
     * <b>Authorization level: </b> all user can create a post.
     * <br>
     * <b>RequestBody: </b> should have the following structure
     * <pre>
     *      {
     *          <b>title: </b> "This key contains String value",
     *          <b>content: </b> "This key contains String value",
     *      }
     * </pre>
     * @param forumPostDTO see RequestBody
     * @param userId used to determine the author
     * @param topicId topic reference for assign post
     * @return {@link ForumPostDTO}
     */
    @PostMapping("/api/v1/user/{userId}/course/{courseId}/topic/{topicId}/post/new")
    public ForumPostDTO createPost(@RequestBody ForumPostDTO forumPostDTO, @PathVariable Integer userId, @PathVariable Integer topicId){
        ForumPostDTO post = new ForumPostDTO(
                null,
                forumPostDTO.title(),
                forumPostDTO.content(),
                topicId,
                userId,
                LocalDate.now(),
                null,
                null
        );

        return postController.createPost(post);
    }

    /**
     * <b>Description: </b> Creates a new {@link Comment}.
     * <br>
     * <b>Authorization level: </b> All user can create a Comment.
     * <br>
     * <b>RequestBody: </b> Should have the following structure.
     * <pre>
     *     {
     *         <b>comment</b>: "This key contains String value"
     *     }
     * </pre>
     * @param commentDTO see RequestBody
     * @param userId used to determine the author
     * @param postId post reference for assign comment
     * @return {@link CommentDTO}
     */
    @PostMapping("/api/v1/user/{userId}/course/{courseId}/topic/{topicId}/post/{postId}/comment/new")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer userId, @PathVariable Integer postId){
        ForumPost thePost = postController.getByDbId(postId);
        UserEntity theUser = userController.getByDbId(userId);
        return commentController.createComment(new Comment(null, commentDTO.comment(), LocalDate.now(),null,thePost,theUser));
    }

    /**
     * <b>Description: </b> Assign a role to user in a course.
     * <br>
     * <b>Authorization level: </b> Only ADMIN and TEACHER can assign a course role.
     * <br>
     * <b>RequestBody: </b> Should have the following structure.
     * <pre>
     *     {
     *         <b>role_id</b>: "This key contains Integer value"
     *     }
     * </pre>
     * @param courseUserRolesDTO see RequestBody
     * @param userId used to determine the authorization level
     * @param courseId used to determine the authorization level
     * @return {@link CourseUserRolesDTO}
     */
    @PostMapping("/api/v1/user/{userId}/course/{courseId}/role")
    public CourseUserRolesDTO assignCourseRole(@RequestBody CourseUserRolesDTO courseUserRolesDTO, @PathVariable Integer userId, @PathVariable Integer courseId){
                CourseUserRolesDTO request = new CourseUserRolesDTO(
                null,
                userId,
                courseUserRolesDTO.roleId(),
                courseId
        );
        return courseRoleController.createRole(request);
    }

    @GetMapping("/api/v1/user/{userId}/courses")
    public List<CourseDTO> getCourseList(){
        return courseController.findAllCourses();
    }


    @GetMapping("api/v1/user//api/v1/user/{userId}/topic/topicList")
    public List<Topic> getListOfTopic(@RequestBody List<Integer> topicList){
        return topicController.getListOfTopic(topicList);
    }

    @GetMapping("api/v1/user//api/v1/user/{userId}/post/postList")
    public List<ForumPost> getListOfPost(@RequestBody List<Integer> postList){
        return postController.getlistOfPosts(postList);
    }

    @GetMapping("api/v1/user//api/v1/user/{userId}/comments/commentList")
    public List<Comment> getListOfComment(@RequestBody List<Integer> commentList){
        return commentController.getlistOfComments(commentList);
    }


    /**
     * <b>Description: </b> Updates a {@link UserEntity}.
     * <br>
     * <b>Authorization level: </b> Only the user can update.
     * <br>
     * <b>RequestBody: </b> Should have the following structure.
     * <pre>
     *     {
     *         <b>displayName</b>: "This key contains String value"
     *     }
     * </pre>
     * @param userEntity see RequestBody
     * @param userId reference to user
     * @return {@link UserEntityDTO}
     * @throws AuthException see Authorization level
     */
    @PutMapping("/api/v1/user/update/{userId}")
    public UserEntityDTO updateUserById(@RequestBody UserEntityDTO userEntity,@PathVariable Integer userId) throws AuthException {
        UserEntityDTO orgUser = userController.getUserById(userId);
        if(userEntity.id() != orgUser.id()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user ID does not match the user to be updated");
        }else {
            return userEntityToUserEntityDTO(userController.updateById(userEntity, userId));
        }
    }

    /**
     * <b>Description: </b> Updates a {@link Course}.
     * <br>
     * <b>Authorization level: </b> Only the ADMIN or TEACHER can update.
     * <br>
     * <b>RequestBody: </b> Should have the following structure.
     * <pre>
     *     {
     *          <b>id</b>: "This key contains Integer value",
     *         <b>courseId</b>*: "This key contains String value",
     *         <b>courseName</b>*: "This key contains String value",
     *         <b>courseDesc</b>*: "This key contains String value"
     *     }
     *     <i>* Only database id is necessary for other fields only the updated fields are necessary</i>
     * </pre>
     * @param courseDTO used for update
     * @param UserId used to determine the authorization level
     * @param courseId used to determine the authorization level
     * @return
     */
    @PutMapping("/api/v1/user/{userId}/course/update/{courseId}")
    public CourseDTO updateCourseById(@RequestBody CourseDTO courseDTO, @PathVariable Integer UserId, Integer courseId){
        Course orgCourse = courseController.findbyDbId(courseId);
        if(orgCourse.getId() != courseDTO.id())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The course ID does not match the course to be updated");
        return courseController.updateByCourseDTO(courseId, courseDTO);
    }

    /**
     * <b>Description: </b> Updates a {@link Topic}.
     * <br>
     * <b>Authorization level: </b> Only the ADMIN, TEACHER or TA can update.
     * <br>
     * <b>RequestBody: </b> Should have the following structure.
     * <pre>
     *     {
     *         <b>topicName</b>*: "This key contains String value",
     *     }
     *     <i>* Only database id is necessary for other fields only the updated fields are necessary</i>
     * </pre>
     * @param topicDTO used to update topic
     * @param UserId used to determine the authorization level
     * @param topicId used to determine the topic
     * @param courseId used to determine the authorization level
     * @return
     */
    @PutMapping("/api/v1/user/{userId}/course/{courseId}/topic/update/{topicId}")
    public TopicDTO updateTopicById(@RequestBody TopicDTO topicDTO, @PathVariable Integer UserId,@PathVariable Integer topicId,@PathVariable Integer courseId){
        TopicDTO org = topicController.findByDbId(topicId);
        if(org.id() != topicDTO.id())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The topic ID does not match the topic to be updated");
        return topicController.updateByTopicDTO(topicId, topicDTO);
    }
    @PutMapping("/api/v1/user/{userId}/course/{courseId}/topic/{topicId}/post/update/{postId}")
    public ForumPostDTO updatePostById(){
        return null;
    }

    /**
     * <b>Description: </b> Updates the {@link Comment}
     * <br>
     * <b>Authorization level: </b> only author of Comment can update a comment.
     * <br>
     * <b>RequestBody: </b> should have the following structure
     * <pre>
     *     {
     *         <b>comment</b>: "This key contains String value"
     *     }
     * </pre>
     * @param commentDTO
     * @param commentId
     * @param userId
     * @return
     */
    @PutMapping("/api/v1/user/{userId}/course/{courseId}/topic/{topicId}/post/{postId}/comment/update/{commentId}")
    public CommentDTO updateCommentById(@RequestBody CommentDTO commentDTO, @PathVariable Integer commentId, @PathVariable Integer userId) throws AuthException {
        CommentDTO commentOrg = commentController.getByDbId(commentId);
        LocalDate created = commentOrg.created();
        ForumPost thePost = postController.getByDbId(commentOrg.postId());
        UserEntity theUser = userController.getByDbId(commentOrg.userId());
        if(commentOrg.userId() != userId){
            throw new AuthException();
        }else{

            commentController.deleteCommentById(commentId, userId);
            return commentController.createComment(new Comment(null, commentOrg.comment(), created,LocalDate.now(),thePost,theUser));
        }

    }


    @DeleteMapping("/api/v1/user/delete/{userId}")
    public void deleteUserById(@PathVariable Integer userId){
        userController.deleteByUserDbId(userId);
    }
    @DeleteMapping("/api/v1/user/{userId}/course/delete/{courseId}")
    public void deleteCourseById(@PathVariable Integer courseId){
        courseController.deleteByCourseDbId(courseId);
    }
    @DeleteMapping("/api/v1/user/{userId}/course/{courseId}/topic/delete/{topicId}")
    public void deleteTopicById(@PathVariable Integer postId){
        topicController.deleteByTopicDbId(postId);
    }
    @DeleteMapping("/api/v1/user/{userId}/course/{courseId}/topic/{topicId}/post/delete/{postId}")
    public void deletePostById(@PathVariable Integer postId){
    postController.removePostByPostId(postId);
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
