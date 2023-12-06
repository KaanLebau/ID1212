package quiz.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.dto.QuestionDTO;
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
        QuizGameModel gameModel = (QuizGameModel) session.getAttribute("game");

        if (gameModel == null) {
            gameModel = new QuizGameModel();

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM TEST.QUESTIONS")) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String text = rs.getString("text");
                    String options = rs.getString("options");
                    String answer = rs.getString("answer");
                    String[] optionsArray = options.split("/");
                    QuestionDTO question = new QuestionDTO(id, text, optionsArray, answer);
                    gameModel.addQuestion(question); // Assuming there's an addQuestion method in QuizGameModel
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
            request.getRequestDispatcher("game.jsp").forward(request, response);
        }

    }
}