package dev.kadan.kthForum.repositories;

import dev.kadan.kthForum.models.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumPostRepository extends JpaRepository<ForumPost, Integer> {
    List<ForumPost> findByTopicId(Integer id);

    List<ForumPost> findByUserId(Integer userId);




}
