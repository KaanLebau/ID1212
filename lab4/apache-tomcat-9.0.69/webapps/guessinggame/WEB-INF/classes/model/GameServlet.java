package model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/game") // Ensure this annotation is uncommented
public class GameServlet extends HttpServlet {
    // ... rest of your servlet code
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the JSP page to show the initial game form
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get or create the session and game state
        HttpSession session = request.getSession();
        GuessGameModel gameModel = (GuessGameModel) session.getAttribute("game");
        if (gameModel == null) {
            gameModel = new GuessGameModel();
            session.setAttribute("game", gameModel);
        }

        // Process the user's guess
        String guessStr = request.getParameter("guess");
        if (guessStr != null && !guessStr.trim().isEmpty()) {
            int guess = Integer.parseInt(guessStr); // Ensure this is a number
            gameModel.clientGuess(guess);
        }

        // Forward to the JSP page to show the result
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }
}