package com.example.lab5.controller;

import com.example.lab5.dto.QuizDTO;
import com.example.lab5.dto.UserDTO;
import com.example.lab5.model.User;
import com.example.lab5.service.QuizServices;
import com.example.lab5.service.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class QuizController {
    private UserServices userServices;
    private QuizServices quizServices;

    public QuizController(UserServices userServices,
                            QuizServices quizServices) {
        this.userServices = userServices;
        this.quizServices = quizServices;
    }

    @GetMapping("")
    public String home(Model model){
        User user =new User();
        model.addAttribute("user", user);
        return"welcome";
    }
    @PostMapping("")
    private String register(@ModelAttribute("user") User user){
        userServices.saveUser(user);
        return "redirect:/quiz";

    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<UserDTO> users = userServices.findAllUser();
        model.addAttribute("users", users);
        return "users-list";
    }


    @GetMapping("/quiz-list")
    public String quizList(Model model){
        List<QuizDTO> quizes = quizServices.findAllQuizes();
        model.addAttribute("quizes", quizes);
        return"quiz-list";
    }

    @GetMapping("/quiz/{id}")
    public String quiz(){
        return"quiz";
    }

    @GetMapping("/result")
    public String result(){
        return"result";
    }
}
