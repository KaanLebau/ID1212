package quiz.controller;

import quiz.dto.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if (user != null) {
            List<ResultDTO> results = fetchResultsForUser(user.getId());
            List<QuizDTO> quizzes = fetchAllQuizzes();

            request.setAttribute("results", results);
            request.setAttribute("quizzes", quizzes);

            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("logout".equals(action)) {
            // Invalidate the session and redirect to login
            HttpSession session = request.getSession(false); // false means don't create if it doesn't exist
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("login");
            return;
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
                        rs.getInt("SCORE"));
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    private List<QuizDTO> fetchAllQuizzes() {
        List<QuizDTO> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM TEST.QUIZZES";
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/quizDB", "test", "test");
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QuizDTO quiz = new QuizDTO(
                        rs.getInt("ID"),
                        rs.getString("SUBJECT"));
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }
}
