package dev.kadan.kthForum.controller.entityController;

import dev.kadan.kthForum.models.Role;
import dev.kadan.kthForum.models.dto.RoleDTO;
import dev.kadan.kthForum.services.impl.RoleServicesImpl;
import dev.kadan.kthForum.utilities.Mapper;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static dev.kadan.kthForum.utilities.Mapper.roleToRoleDTO;

@Controller
public class RoleController {
    private final RoleServicesImpl roleServices;

    public RoleController(RoleServicesImpl roleServices) {
        this.roleServices = roleServices;
    }

    public List<RoleDTO> getAllRoles(){
        List<Role> roleList = roleServices.findAll();
        return roleList.stream().map(Mapper::roleToRoleDTO).collect(Collectors.toList());
    }

    public RoleDTO getByRoleName(String roleName){
        return roleToRoleDTO(roleServices.findByRoleName(roleName));
    }
}
