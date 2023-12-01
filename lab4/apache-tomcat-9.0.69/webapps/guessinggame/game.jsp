<%@ page import="model.GuessGameModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Guessing Game</title>
</head>
<body>
    <h2>Welcome to the Guessing Game!</h2>
    <%
        GuessGameModel game = (GuessGameModel) session.getAttribute("game");
        String message = null;
        if (game != null) {
            message = game.getResult();
        }
    %>
    <form action="game" method="post">
        Guess a number between 1 and 100: <input type="number" name="guess" required>
        <input type="submit" value="Submit">
    </form>
    <% if (message != null) { %>
        <p><%= message %></p>
    <% } %>
    <% if (game != null && !game.isGameIsOn()) { %>
        <p>Game Over. The number was <%= game.getTheNumber() %>. <a href="game">Play again?</a></p>
        <% game.newGame(); // Reset the game for a new round %>
    <% } %>
    <p>Number of attempts: <%= game != null ? game.getAttempt() : 0 %></p>
</body>
</html>