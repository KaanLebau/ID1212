package com.example.lab5.controller;

import com.example.lab5.dto.QuestionsDTO;
import com.example.lab5.dto.QuizzesDTO;
import com.example.lab5.dto.ResultsDTO;
import com.example.lab5.dto.UsersDTO;
import com.example.lab5.model.Questions;
import com.example.lab5.model.Results;
import com.example.lab5.model.Users;
import com.example.lab5.service.QuizService;
import com.example.lab5.service.ResultService;
import com.example.lab5.service.UserService;
import com.example.lab5.util.user;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
public class QuizController {
    private UserService userServices;
    private QuizService quizServices;
    private ResultService resultService;

    public QuizController(UserService userServices,
                          QuizService quizServices,
                          ResultService resultService) {
        this.userServices = userServices;
        this.quizServices = quizServices;
        this.resultService = resultService;
    }

    @GetMapping("")
    public String home(Model model){
        Users user =new Users();
        model.addAttribute("user", user);
        return"welcome";
    }
    @PostMapping("")
    private String register(@ModelAttribute("user") Users user){
        userServices.saveUser(user);
        return "redirect:/quiz";

    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<UsersDTO> users = userServices.findAllUser();
        model.addAttribute("users", users);
        return "users-list";
    }
@GetMapping("/user/{id}")
    public String getTheUser(@PathVariable Integer id, Model model){
        UsersDTO theUser = userServices.findById(id);
        Users argUser= user.mapToUsers(theUser);
        List<ResultsDTO> theResults = resultService.getUserResult(argUser);
        model.addAttribute("theUser", theUser);
        model.addAttribute("theResults", theResults);
        return "user-page";

    }

    @GetMapping("/quiz-list")
    public String quizList(Model model){
        List<QuizzesDTO> quizes = quizServices.findAllQuizzes();
        model.addAttribute("quizes", quizes);
        return"quiz-list";
    }

    @GetMapping("/quiz/{id}")
    public String quiz(@PathVariable Integer id, Model model, @ModelAttribute("result")Results result){
        List<QuestionsDTO> theQuiz = quizServices.getQuizQuestions(id);
        model.addAttribute("theQuiz", theQuiz);
        System.out.println(result);
        return"quiz";
    }

    @PostMapping("/quiz/{id}")
    public void submitQuiz(@PathVariable Integer id,@RequestParam Map<String, String> formParams){
        List<QuestionsDTO> theQuiz = quizServices.getQuizQuestions(id);

            // Compare submittedAnswer with correctAnswers.get(i) to determine correctness
            // You may need to parse the answers appropriately based on your data structure


    }

    @GetMapping("/result")
    public String result(){
        return"result";
    }
}
