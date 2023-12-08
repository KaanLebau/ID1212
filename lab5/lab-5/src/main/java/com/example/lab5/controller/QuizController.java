package com.example.lab5.controller;

import com.example.lab5.dto.*;
import com.example.lab5.model.Quizzes;
import com.example.lab5.model.Results;
import com.example.lab5.model.Users;
import com.example.lab5.service.QuizService;
import com.example.lab5.service.ResultService;
import com.example.lab5.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.lab5.util.Mapper.*;


@Controller
public class QuizController {
    private UserService userServices;
    private QuizService quizServices;
    private ResultService resultService;
    private Users loggedIn;
    private Quizzes currentQuiz;
    private List<QuestionsDTO> currentQuestions;
    private ResultsDTO quizResult;

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
                loggedIn = dummy;
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
        if(this.loggedIn == null)
            return "redirect:/";
        List<UsersDTO> users = userServices.findAllUser();
        model.addAttribute("users", users);
        return "users-list";
    }

    @GetMapping("/user/{id}")
    public String getTheUser(@PathVariable Integer id, Model model){
        if(this.loggedIn == null)
            return "redirect:/";
        UsersDTO theUser = userServices.findById(id);
        Users argUser= mapToUsers(theUser);
        List<ResultsDTO> theResults = resultService.getUserResult(argUser);
        model.addAttribute("theUser", theUser);
        model.addAttribute("theResults", theResults);
        return "user-page";
    }
    @PostMapping("/user/{id}")
    public String selectQuiz(){
        return"redirect:/quiz-list";
    }

    @GetMapping("/quiz-list")
    public String quizList(Model model){
        if(this.loggedIn == null)
            return "redirect:/";
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
        if(this.loggedIn == null)
            return "redirect:/";
        List<QuestionsDTO> questionList = quizServices.getQuizQuestions(id);
        this.currentQuestions = questionList;
        this.currentQuiz = mapToQuizes(quizServices.getByQuizId(id));
        QuizzesDTO currentQuiz = quizServices.findById(id);
        model.addAttribute("currentQuiz", currentQuiz);
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("theQuiz", questionList);
        return"quiz";
    }

    @PostMapping("/quiz/{id}")
    public String submitQuiz(@RequestParam Map<String, String> formParams, @PathVariable Integer id){
        List<String> validate = new ArrayList<>();
        for (int i = 0; i < this.currentQuestions.size(); i++){
            QuestionsDTO q = this.currentQuestions.get(i);
            for(int j =0; j < q.getFacid().length; j++){
                if(q.getFacid()[j].equals("1")){
                    validate.add("question_"+i+"_answer_"+j);
                }
            }
        }
        Integer score = 0;
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(validate.stream().filter(q->q.equals(key)).count()==1)
                score++;
        }

        this.quizResult = new ResultsDTO(this.loggedIn.getId(),this.loggedIn.getUsername(), this.currentQuiz.getId(),score);
        resultService.saveResult(new Results(score, this.loggedIn, this.currentQuiz));
        return "redirect:/result";

    }

    @PostMapping("/result")
    public String showResult(){

        return"redirect:/user/"+this.loggedIn.getId();
    }
    @GetMapping("/result")
    public String result(Model model){
        if(this.loggedIn == null)
            return "redirect:/";
        model.addAttribute("quizResult", this.quizResult);
        return"result";
    }
}
