package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.ForumPost;
import dev.kadan.kthForum.repositories.ForumPostRepository;
import dev.kadan.kthForum.repositories.TopicRepository;
import dev.kadan.kthForum.repositories.UserRepository;
import dev.kadan.kthForum.services.ForumPostServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumPostServicesImpl implements ForumPostServices {
    private ForumPostRepository forumPostRepository;

    public ForumPostServicesImpl(ForumPostRepository forumPostRepository) {
        this.forumPostRepository = forumPostRepository;
    }

    @Override
    public List<ForumPost> getByTopicId(Integer id) {
        return forumPostRepository.findByTopicId(id);
    }

    @Override
    public List<ForumPost> getByUserId(Integer userId) {
        return forumPostRepository.findByUserId(userId);
    }

    @Override
    public ForumPost getByDbId(Integer postId) {
        return forumPostRepository.findById(postId).get();
    }

    @Override
    public ForumPost createPost(ForumPost forumPost) {
        return forumPostRepository.save(forumPost);
    }

    @Override
    public void removePostById(Integer postId) {
        forumPostRepository.deleteById(postId);
    }

    @Override
    public List<ForumPost> findListOfPosts(List<Integer> postIdList) {
        List<ForumPost> postList = new ArrayList<>();
        for( Integer id : postIdList){
            postList.add(forumPostRepository.findById(id).get());
        }
        return postList;
    }

}
