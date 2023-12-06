<%@ page import="java.util.List" %>
<%@ page import="quiz.dto.QuestionDTO" %>
<%@ page import="quiz.model.QuizGameModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css">
    <title>Quiz</title>
</head>
<body>
    <h2>Welcome to the Quiz!</h2>
    <% 
        QuizGameModel gameModel = (QuizGameModel) session.getAttribute("game");
        if (gameModel != null && gameModel.hasMoreQuestions()) {
            QuestionDTO currentQuestion = gameModel.getCurrentQuestion();
            if (currentQuestion != null) {
    %>
                <form action="game" method="post">
                    <p><%= currentQuestion.getText() %></p>
                    <% for (int i = 0; i < currentQuestion.getOptions().length; i++) { %>
                        <input type="checkbox" name="answers" value="<%= i %>" id="option<%= i %>">
                        <label for="option<%= i %>"><%= currentQuestion.getOptions()[i] %></label><br>
                    <% } %>
                    <input type="submit" value="Submit">
                </form>
    <%      }
        } else if (gameModel != null && !gameModel.hasMoreQuestions()) { 
            int finalScore = gameModel.getCurrentScore();
            int totalQuestions = gameModel.getQuestions().size();
    %>
            <h3>Quiz Complete!</h3>
            <p>Your score: <%= finalScore %> out of <%= totalQuestions %>.</p>
            <a href="home">Return home</a>
            <% session.removeAttribute("game"); // Clean up session %>
    <%  } else { %>
            <p>Sorry, there was a problem loading the quiz.</p>
    <%  } %>
</body>
</html>