package lab4.guessing_game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import lab4.guessing_game.dto.*;

/**
 * This class updates the <code>index.html</code> file with new data from the <code>GameController</code>
 *  @author Kaan Özsan
 *  @author Dan Ljungström
 */

@Controller
public class GameController {

    private boolean inputErr;
    private GuessGameModel guessGameModel;
    private GameStateDTO currentState;
    private GameHistoryDTO [] allUserHistory;
    private int currentGuess;
    private int numberOfGames = 1;
    private int sumOfAttempts;
    private double averageGuesses;
    
    
    public GameController() {
        guessGameModel = new GuessGameModel();
        allUserHistory = new GameHistoryDTO[2];
    }

    @GetMapping("")
    public String index(Model model) {
        // Add attributes to the model as needed
        model.addAttribute("GameController", GameController.this);
        return "index"; // Returns the view name (index.jsp)
    }
@PostMapping("")
    public void takeAGuess(String guess) {
        if (inputCheck(guess)){
            inputErr = inputCheck(guess);
        }else {
            sumOfAttempts++;
            inputErr = inputCheck(guess);
            currentGuess = Integer.parseInt(guess);
            guessGameModel.clientGuess(currentGuess);
            currentState = currentGameState();
        }
    }
    public void newGame() {
        averageGuesses = sumOfAttempts / numberOfGames;
        numberOfGames++;
        guessGameModel.newGame();
    }
    private boolean inputCheck(String input) {
        try{
            return Integer.parseInt(input) > 100 && Integer.parseInt(input) <= 0;
        } catch (NumberFormatException e){
            return true;
        }
    }
    public GameStateDTO currentGameState() {
        return new GameStateDTO(guessGameModel, currentGuess);
    }
    public String getResult(){
        if (inputErr)
            return "Invalid input";
        return currentState.toString();
    }
    public void getAllUserHistory(){
        allUserHistory[0] = new GameHistoryDTO(numberOfGames, sumOfAttempts, averageGuesses);
    }

    public GameHistoryDTO getHistory(){
        allUserHistory[1] = new GameHistoryDTO(numberOfGames, sumOfAttempts, averageGuesses);
        return allUserHistory[1];
    }

    public void updateServerHistory(GameHistoryDTO gameHistoryDTO){
        allUserHistory[0] = gameHistoryDTO;
    }
}
