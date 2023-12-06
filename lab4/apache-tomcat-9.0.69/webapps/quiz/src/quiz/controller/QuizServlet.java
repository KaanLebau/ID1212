package quiz.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.dto.QuestionDTO;
import quiz.dto.UserDTO;
import quiz.model.QuizGameModel;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;

@WebServlet("/game")
public class QuizServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Derby driver not found", e);
        }
    }

    private static final String DB_URL = "jdbc:derby://localhost:1527/quizDB";
    private static final String USER = "test";
    private static final String PASS = "test";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        String quizIdParam = request.getParameter("id");
        int quizId = quizIdParam != null ? Integer.parseInt(quizIdParam) : 1;

        QuizGameModel gameModel = (QuizGameModel) session.getAttribute("game");

        if (gameModel == null || gameModel.getQuizId() != quizId) {
            gameModel = new QuizGameModel();
            gameModel.setQuizId(quizId);

            String query = "SELECT q.* FROM TEST.QUESTIONS q " +
                    "JOIN TEST.SELECTOR s ON q.ID = s.QUESTION_ID " +
                    "WHERE s.QUIZ_ID = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, quizId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        QuestionDTO question = new QuestionDTO(
                                rs.getInt("id"),
                                rs.getString("text"),
                                rs.getString("options").split("/"),
                                rs.getString("answer"));
                        gameModel.addQuestion(question);
                    }
                }
            } catch (SQLException e) {
                throw new ServletException("SQL error", e);
            }

            session.setAttribute("game", gameModel);
        }

        request.getRequestDispatcher("game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        QuizGameModel gameModel = (QuizGameModel) session.getAttribute("game");
        if (gameModel == null) {
            gameModel = new QuizGameModel();
        }

        String[] submittedAnswers = request.getParameterValues("answers");
        if (submittedAnswers != null) {
            gameModel.processAnswer(submittedAnswers);
        }

        session.setAttribute("game", gameModel);

        if (gameModel.hasMoreQuestions()) {
            response.sendRedirect("game");
        } else {
            saveResults(gameModel, session);
            request.getRequestDispatcher("game.jsp").forward(request, response);
        }
    }

    private void saveResults(QuizGameModel gameModel, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement pstmt = conn.prepareStatement(
                            "INSERT INTO TEST.RESULTS (USER_ID, QUIZ_ID, SCORE) VALUES (?, ?, ?)")) {

                pstmt.setInt(1, user.getId());
                pstmt.setInt(2, gameModel.getQuizId());
                pstmt.setInt(3, gameModel.getCurrentScore());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}