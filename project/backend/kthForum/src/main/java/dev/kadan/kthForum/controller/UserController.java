package dev.kadan.kthForum.controller;

import dev.kadan.kthForum.models.dto.KthUser;
import dev.kadan.kthForum.models.UserEntity;
import dev.kadan.kthForum.services.impl.UserServiceImpl;
import dev.kadan.kthForum.utilities.SmtpClientWithSTARTTLS;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static dev.kadan.kthForum.utilities.Mapper.kthUserToUserEntity;

@RestController
public class UserController {
    private SmtpClientWithSTARTTLS kthLogin;
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.kthLogin = new SmtpClientWithSTARTTLS();
        this.userService= userService;
    }

    @PostMapping("/api/v1/login")
    public UserEntity login(@RequestBody KthUser kthUser){
        if(kthLogin.logIn(kthUser.username(),kthUser.password())){
            System.out.println("user not found !!!*******************************");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        if(userService.existsByUsername(kthUser.username())){
            System.out.println("user does exist");
            UserEntity user = userService.getByUsername(kthUser.username());
            System.out.println(user);
            return user;
        }
        System.out.println("user does not exist");
        UserEntity user = kthUserToUserEntity(kthUser);
        return userService.saveUser(user);
    }

    @GetMapping("/api/v1/users")
    public UserEntity getAll(){
        return null;
    }


}
