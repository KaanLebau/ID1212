package com.project.CourseForum.controller;

import com.project.CourseForum.model.Users;
import com.project.CourseForum.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    private UserService userServices;
    private Users loggedIn;

    public HomeController(UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/home")
    public String home(Model model){
        Users user =new Users();
        model.addAttribute("user", user);
        return "home.html";
    }

    @PostMapping("/home")
    public String login(@ModelAttribute("user") Users user){
        try{
            Users dummy = userServices.findByUsername(user.getUsername());
            if(dummy.equals((Object)user)){
                loggedIn = dummy;
                return "home.html";
            }else {
                return "home.html";
            }
        }catch (Exception e){
            userServices.saveUser(user);
        }
        return "home.html";
    }
}
