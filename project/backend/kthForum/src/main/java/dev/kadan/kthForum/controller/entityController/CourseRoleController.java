package dev.kadan.kthForum.controller.entityController;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.CourseUserRoles;
import dev.kadan.kthForum.models.Role;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.models.dto.CourseUserRolesDTO;
import dev.kadan.kthForum.services.impl.CourseRoleServicesImpl;
import dev.kadan.kthForum.services.impl.CourseServicesImpl;
import dev.kadan.kthForum.services.impl.RoleServicesImpl;
import dev.kadan.kthForum.services.impl.UserServiceImpl;
import dev.kadan.kthForum.utilities.Mapper;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static dev.kadan.kthForum.utilities.Mapper.courseRoleToCourseRoleDTO;

@Controller
public class CourseRoleController {
    private final CourseRoleServicesImpl courseRoleServices;
    private final CourseServicesImpl courseServices;
    private final UserServiceImpl userService;
    private final RoleServicesImpl roleServive;

    public CourseRoleController(CourseRoleServicesImpl courseRoleServices, CourseServicesImpl courseServices, UserServiceImpl userService, RoleServicesImpl roleServive) {
        this.courseRoleServices = courseRoleServices;
        this.courseServices = courseServices;
        this.userService = userService;
        this.roleServive = roleServive;
    }

    public CourseUserRolesDTO createRole(CourseUserRolesDTO courseUserRolesDTO){
        return courseRoleToCourseRoleDTO(courseRoleServices.createCourseRole(new CourseUserRoles(
                null,
                userService.findByDbId(courseUserRolesDTO.userId()),
                roleServive.findByDbId(courseUserRolesDTO.roleId()),
                courseServices.findByDbId(courseUserRolesDTO.courseId())
        )));
    }

    public List<CourseUserRolesDTO> getRolesByCourseId(Integer courseId){
        Course theCourse = courseServices.findByDbId(courseId);
        List<CourseUserRoles> rolesList = courseRoleServices.findByCourse(theCourse);
        return rolesList.stream().map(Mapper::courseRoleToCourseRoleDTO).collect(Collectors.toList());
    }

    public List<CourseUserRolesDTO> getRolesByUserId(Integer userId){
        UserEntity theUser = userService.findByDbId(userId);
        List<CourseUserRoles> rolesList = courseRoleServices.findByUser(theUser);
        return rolesList.stream().map(Mapper::courseRoleToCourseRoleDTO).collect(Collectors.toList());
    }
    public List<CourseUserRolesDTO> getRolesByRoleId(Integer roleId){
        Role theRole = roleServive.findByDbId(roleId);
        List<CourseUserRoles> rolesList = courseRoleServices.findByRole(theRole);
        return rolesList.stream().map(Mapper::courseRoleToCourseRoleDTO).collect(Collectors.toList());
    }
    public CourseUserRolesDTO getRoleByCourseAndUser(Course course, UserEntity user){

        return courseRoleToCourseRoleDTO(courseRoleServices.findByCourseAndUser(course, user));
    }
    public CourseUserRoles getRoleByCourseAndUserIds(Integer course, Integer user){
        UserEntity theUser = userService.findByDbId(course);
        Course theCourse = courseServices.findByDbId(user);
        CourseUserRoles foundRole = courseRoleServices.findByCourseAndUser(theCourse, theUser);
        return foundRole;
    }

    public CourseUserRolesDTO updateRolesByRoleId(CourseUserRolesDTO courseUserRolesDTO){
        UserEntity theUser = userService.findByDbId(courseUserRolesDTO.userId());
        Course theCourse = courseServices.findByDbId(courseUserRolesDTO.courseId());
        Role theRole = roleServive.findByDbId(courseUserRolesDTO.roleId());
        CourseUserRoles orgRole = courseRoleServices.findByCourseAndUser(theCourse, theUser);
        courseRoleServices.removeCourseRole(orgRole.getId());
        orgRole.setId(null);
        orgRole.setRole(theRole);
        return courseRoleToCourseRoleDTO(orgRole);
    }

    public void removeRoleByRoleId(Integer courseUserRolesId){
        courseRoleServices.removeCourseRole(courseUserRolesId);
    }



}
