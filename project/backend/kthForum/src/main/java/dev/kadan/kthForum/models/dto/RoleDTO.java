package dev.kadan.kthForum.models.dto;

import java.util.List;

public record RoleDTO(
        Integer id,
        String roleName,
        List<CourseUserRolesDTO> courseRoles
) {
}
