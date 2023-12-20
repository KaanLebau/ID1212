package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.dto.ForumPostDTO;

import java.util.List;

public interface ForumPostServices {

    List<ForumPostDTO> getByToipcId(Integer id);

    void createPost();
}
