package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.UserEntity;

import java.util.List;

public interface UserServices {

    UserEntity getByUsername(String username);

    UserEntity getByDbId(Integer userId);

    UserEntity saveUser(UserEntity user);

    boolean existsByUsername(String username);

    void removeUserById(Integer id);
    List<UserEntity> findListOfUser(List<Integer> userIdList);

}
