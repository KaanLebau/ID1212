package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.CourseUserRoles;
import dev.kadan.kthForum.models.Role;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.repositories.CourseRoleRepository;
import dev.kadan.kthForum.services.CourseRoleServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseRoleServicesImpl implements CourseRoleServices {
    public final CourseRoleRepository roleRepository;

    public CourseRoleServicesImpl(CourseRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public CourseUserRoles createCourseRole(CourseUserRoles courseUserRoles) {
        return roleRepository.save(courseUserRoles);
    }

    @Override
    public List<CourseUserRoles> findByCourse(Course course) {
        return roleRepository.findByCourse(course);
    }

    @Override
    public List<CourseUserRoles> findByUser(UserEntity user) {
        return roleRepository.findByUser(user);
    }

    @Override
    public List<CourseUserRoles> findByRole(Role role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public CourseUserRoles findByDbId(Integer dbId) {
        return roleRepository.findById(dbId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Course role do not exist"));
    }

    @Override
    public CourseUserRoles findByCourseAndUser(Course course, UserEntity user) {
        return roleRepository.findByCourseAndUser(course, user);
    }

    @Override
    public void removeCourseRole(Integer courseUserRolesId) {
        roleRepository.deleteById(courseUserRolesId);
    }
}
