package quiz.controller;

import quiz.dto.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assume that we have a user object in the session
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if (user != null) {
            // Fetch user results and quizzes from the database
            List<ResultDTO> results = fetchResultsForUser(user.getId());
            List<QuizDTO> quizzes = fetchAllQuizzes();

            // Set the results and quizzes in the request to be used in the JSP
            request.setAttribute("results", results);
            request.setAttribute("quizzes", quizzes);

            // Forward to the homepage JSP
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else {
            // If no user is in session, redirect to login
            response.sendRedirect("login");
        }
    }

private List<ResultDTO> fetchResultsForUser(int userId) {
    List<ResultDTO> results = new ArrayList<>();
    String sql = "SELECT * FROM TEST.RESULTS WHERE USER_ID = ?";
    try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/quizDB", "test", "test");
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ResultDTO result = new ResultDTO(
                rs.getInt("ID"),
                rs.getInt("USER_ID"),
                rs.getInt("QUIZ_ID"),
                rs.getInt("SCORE")
            );
            results.add(result);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Proper logging recommended
    }
    return results;
}


    // Example method to fetch quizzes - implement this according to your DB logic
    private List<QuizDTO> fetchAllQuizzes() {
        List<QuizDTO> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM TEST.QUIZZES";
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/quizDB", "test", "test");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QuizDTO quiz = new QuizDTO(
                    rs.getInt("ID"),
                    rs.getString("SUBJECT")
                );
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper logging recommended
        }
        return quizzes;
    }
}
