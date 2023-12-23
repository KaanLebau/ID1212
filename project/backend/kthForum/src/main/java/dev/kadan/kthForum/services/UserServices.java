package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.UserEntity;

public interface UserServices {

    UserEntity getByUsername(String username);

    UserEntity getByDbId(Integer userId);

    UserEntity saveUser(UserEntity user);

    boolean existsByUsername(String username);


}
