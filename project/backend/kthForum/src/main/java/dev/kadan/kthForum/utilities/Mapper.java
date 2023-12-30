package dev.kadan.kthForum.utilities;

import dev.kadan.kthForum.models.*;
import dev.kadan.kthForum.models.dto.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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
 *      <li><code>userEntityToUserEntityDTO({@link UserEntity} userEntity)</code></li>
 *      <li><code>courseRoleToCourseRoleDTO ({@link CourseUserRoles} courseUserRoles)</code></li>
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
                getTopicIdList(course.getTopicList()),
                course.getCourseRole().stream().map(Mapper::courseRoleToCourseRoleDTO).collect(Collectors.toList())
        );}

    public static Course courseDTOToCourses(CourseDTO courseDTO){
        return new Course(
                courseDTO.id(),
                courseDTO.courseId(),
                courseDTO.courseName(),
                courseDTO.courseDesc(),
                null,
                null
        );
    }
    public static TopicDTO topicToTopicDTO(Topic topic){
        return new TopicDTO(
                topic.getId(),
                topic.getTopicName(),
                topic.getCourses().getId(),
                getPostIdList(topic.getPostList())
                );}
    public static Topic topicDTOToTopic(TopicDTO topicDTO){
        return new Topic(
                topicDTO.id(),
                topicDTO.topicName(),
                null,
                null
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
                getCommentIdList(forumPost.getCommentList())
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
                null

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

public static UserEntityDTO userEntityToUserEntityDTO(UserEntity userEntity){
        return new UserEntityDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getDisplayName(),
                userEntity.getEmail(),
                getPostIdList(userEntity.getPostList()),
                getCommentIdList(userEntity.getCommentList()),
                userEntity.getCourseRole().stream().map(Mapper::courseRoleToCourseRoleDTO).collect(Collectors.toList())

        );
}

public static CourseUserRolesDTO courseRoleToCourseRoleDTO (CourseUserRoles courseUserRoles){
        return new CourseUserRolesDTO(
                courseUserRoles.getId(),
                courseUserRoles.getUser().getId(),
                courseUserRoles.getRole().getId(),
                courseUserRoles.getCourse().getId()
        );
}
public static RoleDTO roleToRoleDTO(Role role){
        return new RoleDTO(
                role.getId(),
                role.getRoleName(),
                null
        );
}

private static List<Integer> getPostIdList(List<ForumPost> posts){
    List<Integer> ids =new ArrayList<>();
    for (ForumPost post : posts){
        ids.add(post.getId());
    }
    return ids;
}

    private static List<Integer> getTopicIdList(List<Topic> topics){
        List<Integer> ids =new ArrayList<>();
        for (Topic topic : topics){
            ids.add(topic.getId());
        }
        return ids;
    }
    private static List<Integer> getCommentIdList(List<Comment> comments){
        List<Integer> ids =new ArrayList<>();
        for (Comment comment : comments){
            ids.add(comment.getId());
        }
        return ids;
    }

    private static List<Integer> getCourseIdList(List<Course> courses){
        List<Integer> ids =new ArrayList<>();
        for (Course course : courses){
            ids.add(course.getId());
        }
        return ids;
    }



}

