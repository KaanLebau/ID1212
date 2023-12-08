package com.example.lab5.controller;

import com.example.lab5.dto.*;
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
    private Users loggedIn;

    private List<QuestionsDTO> currentQuiz;

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
    public String login(@ModelAttribute("user") Users user){
        try{
            Users dummy = userServices.findByUsername(user.getUsername());
            if(dummy.equals((Object)user)){
                loggedIn = user;
                return "redirect:/quiz-list";
            }else {
                return "redirect:/";
            }
        }catch (Exception e){
            userServices.saveUser(user);
        }
        return "redirect:/quiz-list";
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

    @PostMapping("/quiz-list")
    public String takeQuiz(@ModelAttribute("selectedQuizId") SelectedQuizDTO quiz){
        System.out.println("/quiz/" + quiz.id());
        System.out.println(quiz);
        return "redirect:/quiz/" + quiz.id();
    }

    @GetMapping("/quiz/{id}")
    public String quiz(@PathVariable Integer id, Model model){
        List<QuestionsDTO> theQuiz = quizServices.getQuizQuestions(id);
        this.currentQuiz = theQuiz;
        QuizzesDTO currentQuiz = quizServices.findById(id);
        model.addAttribute("currentQuiz", currentQuiz);
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("theQuiz", theQuiz);
        return"quiz";
    }

    @PostMapping("/quiz/{id}")
    public String submitQuiz(@RequestParam Map<String, String> formParams){
        System.out.println(formParams);
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // Check if the key represents a checkbox value
            if (key.startsWith("checkbox_")) {
                // Extract the question ID from the key
                String questionId = key.substring("checkbox_".length());

                // Determine if the checkbox was checked (value will be "on" if checked)
                boolean isChecked = "on".equals(value);

                // Do something with the information (e.g., store in a result object)
                System.out.println("Question ID: " + questionId + ", Answered: " + isChecked);
            }
        }


        return "";

    }

    @PostMapping("/result")
    public String showResult(){
        return"result";
    }
    @GetMapping("/result")
    public String result(){
        return"result";
    }
}
