package dev.kadan.kthForum.services.impl;


import dev.kadan.kthForum.models.Role;
import dev.kadan.kthForum.repositories.RoleRepository;
import dev.kadan.kthForum.services.RoleServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoleServicesImpl implements RoleServices {
    private final RoleRepository roleRepository;

    public RoleServicesImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByDbId(Integer id) {
        return roleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Role is not exist"));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
