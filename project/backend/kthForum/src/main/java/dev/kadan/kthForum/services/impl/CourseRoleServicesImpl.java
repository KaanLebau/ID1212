package dev.kadan.kthForum.services.impl;

import dev.kadan.kthForum.models.CourseUserRoles;
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
    public List<CourseUserRoles> findByCourse(Integer courseId) {
        return null;
    }

    @Override
    public List<CourseUserRoles> findByUser(Integer userId) {
        return roleRepository.findByUserId(userId);
    }

    @Override
    public List<CourseUserRoles> findByRole(Integer roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    @Override
    public CourseUserRoles findByDbId(Integer dbId) {
        return roleRepository.findById(dbId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Course role do not exist"));
    }

    @Override
    public void removeCourseRole(CourseUserRoles courseUserRoles) {

    }
}
