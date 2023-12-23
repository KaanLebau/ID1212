package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.ForumPost;
import dev.kadan.kthForum.models.dto.ForumPostDTO;

import java.util.List;

public interface ForumPostServices {

    List<ForumPost> getByTopicId(Integer id);

    List<ForumPost> getByUserId(Integer id);

    ForumPost getByDbId(Integer Id);

    ForumPost createPost(ForumPost forumPost);

    void removePostById(Integer postId);
}
