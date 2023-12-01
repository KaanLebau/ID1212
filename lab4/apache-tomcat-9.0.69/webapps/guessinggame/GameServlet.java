import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Optional: Use the @WebServlet annotation to configure the servlet
// @WebServlet("/play")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the JSP page to show the initial game form
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get or create the session and game state
        HttpSession session = request.getSession();
        GameStateDTO gameState = (GameStateDTO) session.getAttribute("gameState");
        if (gameState == null) {
            gameState = new GameStateDTO();
            session.setAttribute("gameState", gameState);
        }

        // Process the user's guess
        String guess = request.getParameter("guess");
        GuessGameModel gameModel = new GuessGameModel();
        boolean isCorrect = gameModel.evaluateGuess(guess, gameState);
        
        // Update the session with the new game state
        session.setAttribute("gameState", gameState);

        // Redirect or forward to the appropriate JSP page to show the result
        if (isCorrect) {
            request.getRequestDispatcher("/correct.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/tryAgain.jsp").forward(request, response);
        }
    }
}
