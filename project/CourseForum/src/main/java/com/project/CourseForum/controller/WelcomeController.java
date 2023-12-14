package com.project.CourseForum.controller;

import com.project.CourseForum.model.Users;
import com.project.CourseForum.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class WelcomeController {
    private UserService userServices;
    private Users loggedIn;

    public WelcomeController(UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping("")
    public String home(Model model){
        Users user =new Users();
        model.addAttribute("user", user);
        return "welcome.html";
    }

    @PostMapping("")
    public String login(@ModelAttribute("user") Users user){
        try{
            Users dummy = userServices.findByUsername(user.getUsername());
            if(dummy.equals((Object)user)){
                loggedIn = dummy;
                return "welcome";
            }else {
                return "welcome";
            }
        }catch (Exception e){
            userServices.saveUser(user);
        }
        return "welcome";
    }
}
