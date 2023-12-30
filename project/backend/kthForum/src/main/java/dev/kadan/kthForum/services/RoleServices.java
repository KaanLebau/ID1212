package dev.kadan.kthForum.services;

import dev.kadan.kthForum.models.Role;

import java.util.List;

public interface RoleServices {
    Role findByDbId(Integer id);

    List<Role> findAll();
}
