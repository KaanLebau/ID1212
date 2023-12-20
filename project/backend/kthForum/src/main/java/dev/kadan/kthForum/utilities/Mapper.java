package dev.kadan.kthForum.utilities;

import dev.kadan.kthForum.models.Courses;
import dev.kadan.kthForum.models.ForumPost;
import dev.kadan.kthForum.models.Topic;
import dev.kadan.kthForum.models.dto.ForumPostDTO;
import dev.kadan.kthForum.models.dto.KthUser;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.models.dto.CourseDTO;
import dev.kadan.kthForum.models.dto.TopicDTO;

import java.util.stream.Collectors;

public class Mapper {

    public static UserEntity kthUserToUserMapping(KthUser kthUser){
        return new UserEntity(null, kthUser.username(), null);
    }
    public static CourseDTO courseToCourseDTO(Courses course){
        return new CourseDTO(
                course.getId(),
                course.getCourseId(),
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTopicList().stream().map(Mapper::topicToTopicDTO).collect(Collectors.toList()));}

    public static TopicDTO topicToTopicDTO(Topic topic){
        return new TopicDTO(
                topic.getId(),
                topic.getTopicName(),
                topic.getPostList().stream().map(Mapper::forumPostToForumPostDTO).collect(Collectors.toList())
                );}
public static ForumPostDTO forumPostToForumPostDTO(ForumPost forumPost){
        return new ForumPostDTO(
                forumPost.getAuthoId(),
                forumPost.getAuthorName(),
                forumPost.getTitle(),
                forumPost.getContent(),
                forumPost.getCreated(),
                forumPost.getUpdated()
        );
}

}

