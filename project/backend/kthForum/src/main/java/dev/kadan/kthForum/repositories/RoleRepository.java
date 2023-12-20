package dev.kadan.kthForum.repositories;

import dev.kadan.kthForum.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(String roleName);
}
