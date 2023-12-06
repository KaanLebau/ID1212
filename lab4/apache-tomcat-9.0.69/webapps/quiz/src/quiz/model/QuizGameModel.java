package quiz.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import quiz.dto.QuestionDTO;

public class QuizGameModel {
    private List<QuestionDTO> questions;
    private int currentScore;
    private int currentQuestionIndex;

    public QuizGameModel() {
        this.questions = new ArrayList<>();
        this.currentScore = 0;
        this.currentQuestionIndex = 0;
    }

    public void addQuestion(QuestionDTO question) {
        questions.add(question);
    }

    public QuestionDTO getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }
    
public void processAnswer(String[] submittedAnswers) {
    QuestionDTO currentQuestion = getCurrentQuestion();
    if (currentQuestion != null) {
        // Assuming the answer field is like "0/0/1" where "1" indicates the correct answer
        String[] correctAnswers = currentQuestion.getAnswer().split("/");
        int correctCount = 0;

        for (String answer : submittedAnswers) {
            int answerIndex = Integer.parseInt(answer);
            if ("1".equals(correctAnswers[answerIndex])) {
                correctCount++;
            }
        }
        
        // Only increment score if all correct answers are selected and no incorrect ones
        if (correctCount == submittedAnswers.length && correctCount == Arrays.stream(correctAnswers).filter(a -> "1".equals(a)).count()) {
            currentScore++;
        }
        currentQuestionIndex++;
    }
}


    public int getCurrentScore() {
        return currentScore;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }

    public void resetGame() {
        currentScore = 0;
        currentQuestionIndex = 0;
    }

    // Getters and setters
    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public int getTotalQuestions() {
        return questions.size();
    }    
}