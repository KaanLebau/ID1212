<%@ page import="java.util.List" %>
<%@ page import="quiz.dto.ResultDTO" %>
<%@ page import="quiz.dto.QuizDTO" %>
<%@ page import="quiz.dto.UserDTO" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css">
    <title>Homepage</title>
</head>
<body>
    <% UserDTO user = (UserDTO) session.getAttribute("user"); %>
    <h1>Welcome, <%= user.getUsername() %>!</h1>

    <h2>Past Results</h2>
    <ul>
        <% 
            List<ResultDTO> results = (List<ResultDTO>) request.getAttribute("results");
            for (ResultDTO result : results) {
                out.println("<li>" + result.getQuizId() + " - Score: " + result.getScore() + "</li>");
            }
        %>
    </ul>

    <h2>Available Quizzes</h2>
    <ul>
        <% 
            List<QuizDTO> quizzes = (List<QuizDTO>) request.getAttribute("quizzes");
            for (QuizDTO quiz : quizzes) {
                out.println("<li><a href='game?id=" + quiz.getId() + "'>" + quiz.getSubject() + "</a></li>");
            }
        %>
    </ul>

    <form action="home" method="post">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="Logout">
    </form>    
</body>
</html>