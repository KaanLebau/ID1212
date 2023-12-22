package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Enums.Roles;
import dev.kadan.kthForum.models.Role;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.repositories.RoleRepository;
import dev.kadan.kthForum.repositories.UserRepository;
import dev.kadan.kthForum.services.UserServices;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
    public UserEntity saveUser(UserEntity user) {
        UserEntity userToAdd = user;
        Role role = roleRepository.findByRoleName(String.valueOf(Roles.STUDENT));
        System.out.println(role.getRoleName());
        userToAdd.setRoleList(Collections.singletonList(role));
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
