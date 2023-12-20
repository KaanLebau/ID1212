package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.ForumPost;
import dev.kadan.kthForum.models.dto.ForumPostDTO;
import dev.kadan.kthForum.repositories.ForumPostRepository;
import dev.kadan.kthForum.repositories.TopicRepository;
import dev.kadan.kthForum.repositories.UserRepository;
import dev.kadan.kthForum.services.ForumPostServices;
import dev.kadan.kthForum.utilities.Mapper;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForumPostImpl implements ForumPostServices {
    private ForumPostRepository forumPostRepository;
    private UserRepository userRepository;
    private TopicRepository topicRepository;
    public ForumPostImpl(ForumPostRepository forumPostRepository) {
        this.forumPostRepository = forumPostRepository;
    }

    @Override
    public List<ForumPostDTO> getByToipcId(Integer id) {
        List<ForumPost> postList = forumPostRepository.findByTopicId(id);
        return postList.stream().map(Mapper::forumPostToForumPostDTO).collect(Collectors.toList());
    }

    @Override
    public void createPost() {

    }
}
