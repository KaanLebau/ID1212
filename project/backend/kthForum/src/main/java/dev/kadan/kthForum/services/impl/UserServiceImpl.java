package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Enums.Roles;
import dev.kadan.kthForum.models.Role;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.repositories.RoleRepository;
import dev.kadan.kthForum.repositories.UserRepository;
import dev.kadan.kthForum.services.UserServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findByDbId(Integer userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void removeUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> findListOfUser(List<Integer> userIdList) {
        List<UserEntity> userList = new ArrayList<>();
        for(Integer id : userIdList){
            userList.add(userRepository.findById(id).get());
        }
        return userList;
    }

    @Override
    public UserEntity updateByDbId(Integer userId, UserEntity user) {
        UserEntity theUser = userRepository.findById(userId).get();
        theUser.setDisplayName(user.getDisplayName());
        UserEntity updated = userRepository.save(theUser);
        return updated;
    }
}
