package dev.kadan.kthForum.utilities;

import dev.kadan.kthForum.models.*;
import dev.kadan.kthForum.models.dto.*;



/**
 * This class is used when converting from object to DTO and TDO to object. the following methods are available
 * <ul>
 *     <li><code>kthUserToUserEntity(KthUser kthUser)</code></li>
 *     <li><code>courseToCourseDTO({@link Course} course)</code></li>
 *     <li><code>courseDTOToCourses({@link CourseDTO} courseDTO)</code></li>
 *     <li><code>topicToTopicDTO({@link Topic} topic)</code></li>
 *     <li><code>topicDTOToTopic({@link TopicDTO} topicDTO)</code></li>
 *     <li><code>forumPostToForumPostDTO({@link ForumPost} forumPost)</code></li>
 *      <li><code>forumPostDTOToForumPost({@link ForumPostDTO} forumPostDTO)</code></li>
 *      <li><code>commentToCommentDTO({@link Comment} comment)</code></li>
 *      <li><code>commentDTOToComment({@link CommentDTO} commentDTO)</code></li>
 * </ul>
 */
public class Mapper {

    public static UserEntity kthUserToUserEntity(KthUser kthUser){
        return new UserEntity(null, kthUser.username(), null);
    }
    public static CourseDTO courseToCourseDTO(Course course){
        return new CourseDTO(
                course.getId(),
                course.getCourseId(),
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTopicList());}

    public static Course courseDTOToCourses(CourseDTO courseDTO){
        return new Course(
                courseDTO.id(),
                courseDTO.courseId(),
                courseDTO.courseName(),
                courseDTO.courseDesc(),
                courseDTO.topicList()
        );
    }
    public static TopicDTO topicToTopicDTO(Topic topic){
        return new TopicDTO(
                topic.getId(),
                topic.getTopicName(),
                topic.getCourses().getId(),
                topic.getPostList()
                );}
    public static Topic topicDTOToTopic(TopicDTO topicDTO){
        return new Topic(
                topicDTO.id(),
                topicDTO.topicName(),
                null,
                topicDTO.postList()
        );
    }
public static ForumPostDTO forumPostToForumPostDTO(ForumPost forumPost){
        return new ForumPostDTO(
                forumPost.getId(),
                forumPost.getTitle(),
                forumPost.getContent(),
                forumPost.getTopic().getId(),
                forumPost.getUser().getId(),
                forumPost.getCreated(),
                forumPost.getUpdated(),
                forumPost.getCommentList()
        );
}
public static ForumPost forumPostDTOToForumPost(ForumPostDTO forumPostDTO){
        return new ForumPost(
                forumPostDTO.title(),
                null,
                forumPostDTO.content(),
                null,
                forumPostDTO.created(),
                forumPostDTO.updated(),
                forumPostDTO.commentList()

        );
}

public static CommentDTO commentToCommentDTO(Comment comment){
        return new CommentDTO(
                comment.getId(),
                comment.getComment(),
                comment.getPosts().getId(),
                comment.getUser().getId(),
                comment.getCreated(),
                comment.getUpdated());
}
public static Comment commentDTOToComment(CommentDTO commentDTO){
        return new Comment(
                commentDTO.id(),
                commentDTO.comment(),
                commentDTO.created(),
                commentDTO.updated(),
                null,
                null
        );
}

}

