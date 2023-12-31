package dev.kadan.kthForum.controller.entityController;

import dev.kadan.kthForum.models.Course;
import dev.kadan.kthForum.models.CourseUserRoles;
import dev.kadan.kthForum.models.ForumPost;
import dev.kadan.kthForum.models.dto.KthUser;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.models.dto.UserEntityDTO;
import dev.kadan.kthForum.services.impl.UserServiceImpl;
import dev.kadan.kthForum.utilities.Mapper;
import dev.kadan.kthForum.utilities.SmtpClientWithSTARTTLS;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static dev.kadan.kthForum.utilities.Mapper.kthUserToUserEntity;
import static dev.kadan.kthForum.utilities.Mapper.userEntityToUserEntityDTO;


@Controller
public class UserController {
    private SmtpClientWithSTARTTLS kthLogin;
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.kthLogin = new SmtpClientWithSTARTTLS();
        this.userService= userService;
    }


    public UserEntityDTO login(@RequestBody KthUser kthUser){
        if(!kthLogin.logIn(kthUser.username(),kthUser.password())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
        UserEntity user;
        if(userService.existsByUsername(kthUser.username())){
            user = userService.getByUsername(kthUser.username());
        }else{
            user = userService.saveUser(kthUserToUserEntity(kthUser));
        }
        return userEntityToUserEntityDTO(user);
    }

    public List<Course> getUsersListOfCourses(Integer userId){
        List<Course> courseList = new ArrayList<>();
        UserEntity theUser = userService.findByDbId(userId);
        for(CourseUserRoles course : theUser.getCourseRole()){
            courseList.add(course.getCourse());
        }
        return courseList;
    }

    public List<ForumPost> getUsersListOfPosts(Integer userId){
        return userService.findByDbId(userId).getPostList();
    }


    public UserEntity getAll(){
        return null;
    }

    public UserEntityDTO getUserById(Integer userId){
        UserEntity theUser = userService.findByDbId(userId);
        return userEntityToUserEntityDTO(theUser);
    }
    public List<UserEntity> getUserList(List<Integer> idList){
        return userService.findListOfUser(idList);
    }
    public UserEntity getByDbId(Integer userId){
        return userService.findByDbId(userId);
    }

    public UserEntity updateById(UserEntityDTO userEntityDTO, Integer userId){
        UserEntity theUser = userService.findByDbId(userId);
        theUser.setDisplayName(userEntityDTO.displayName());
        return userService.updateByDbId(userId, theUser);
    }


    public void deleteByUserDbId(Integer userId) {
        userService.removeUserById(userId);
    }
}
