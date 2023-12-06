<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css">
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <%
        String loginError = (String) request.getAttribute("loginError");
        if (loginError != null) {
            out.println("<p style='color:red;'>" + loginError + "</p>");
        }
    %>
    <form action="login" method="post">
        Email: <input type="text" name="email"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>