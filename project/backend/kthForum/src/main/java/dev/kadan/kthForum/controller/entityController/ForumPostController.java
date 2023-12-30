package dev.kadan.kthForum.controller.entityController;

import dev.kadan.kthForum.models.ForumPost;
import dev.kadan.kthForum.models.Topic;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.models.dto.ForumPostDTO;
import dev.kadan.kthForum.services.ForumPostServices;
import dev.kadan.kthForum.services.impl.TopicServecesImpl;
import dev.kadan.kthForum.services.impl.UserServiceImpl;
import dev.kadan.kthForum.utilities.Mapper;
import jakarta.security.auth.message.AuthException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static dev.kadan.kthForum.utilities.Mapper.forumPostDTOToForumPost;
import static dev.kadan.kthForum.utilities.Mapper.forumPostToForumPostDTO;

@RestController
public class ForumPostController {
    private final ForumPostServices postServices;
    private final UserServiceImpl userService;
    private final TopicServecesImpl topicServeces;
    private final String  BASE_PATH ="/api/v1/posts/";

    public ForumPostController(ForumPostServices postServices, UserServiceImpl userService, TopicServecesImpl topicServeces) {
        this.postServices = postServices;
        this.userService = userService;
        this.topicServeces = topicServeces;
    }

    /*
        CREATE
    */
    @PostMapping(BASE_PATH + "new")
    public ForumPostDTO createPost(@RequestBody ForumPostDTO postDTO){
        UserEntity theUser = userService.findByDbId(postDTO.userId());
        Topic theTopic = topicServeces.getByDbId(postDTO.topicId());
        ForumPost thePost = forumPostDTOToForumPost(postDTO);
        thePost.setUser(theUser);
        thePost.setTopic(theTopic);
        thePost.setCreated(LocalDate.now());
        return forumPostToForumPostDTO(postServices.createPost(thePost));
    }

    /*
        READ
     */
    @GetMapping(BASE_PATH + "post/getbyuser/{userId}")
    public List<ForumPostDTO> getPostByUserId(@PathVariable Integer userId){
        UserEntity theUser = userService.findByDbId(userId);
        return theUser.getPostList().stream().map(Mapper::forumPostToForumPostDTO).collect(Collectors.toList());
    }

    @GetMapping(BASE_PATH + "post/getbytopic/{topicId}")
    public List<ForumPostDTO> getPostByTopicId(@PathVariable Integer topicId){
        Topic theTopic = topicServeces.getByDbId(topicId);
        return theTopic.getPostList().stream().map(Mapper::forumPostToForumPostDTO).collect(Collectors.toList());
    }

    /**
     * getbyDbId() returns Post identified by primary key
     * @param postId db primary key
     * @return {@link ForumPost}
     */
    public ForumPost getByDbId(Integer postId){
        return postServices.getByDbId(postId);
    }

    /*
        UPDATE
     */
    @PutMapping(BASE_PATH + "update/{postId}")
    public ForumPostDTO updatePost(@RequestBody ForumPostDTO forumPostDTO, @PathVariable Integer postId) throws AuthException {
        ForumPost newPost = null;
        ForumPost oldPost = postServices.getByDbId(postId);
        if(oldPost.getUser().getId() != forumPostDTO.userId()){
            throw new AuthException();
        }else {
            UserEntity theUser = userService.findByDbId(forumPostDTO.userId());
            Topic theTopic = topicServeces.getByDbId(forumPostDTO.topicId());
            LocalDate created = oldPost.getCreated();
            newPost = forumPostDTOToForumPost(forumPostDTO);
            newPost.setTopic(theTopic);
            newPost.setUser(theUser);
            newPost.setCreated(created);
            newPost.setUpdated(LocalDate.now());
            postServices.removePostById(postId);
            return forumPostToForumPostDTO(postServices.createPost(newPost));
        }
    }


    public void removePostByPostId(Integer postId){
        postServices.removePostById(postId);
    }


}
