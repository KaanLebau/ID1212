/**
 * This class updates the <code>index.html</code> file with new data from the <code>GameController</code>
 *  @author Kaan Özsan
 *  @author Dan Ljungström
 */
public class GameController {

    private boolean inputErr;
    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private GameStateDTO currentState;
    private int currentGuess;

    public GameController() {
        guessGameModel = new GuessGameModel();
        pageHandler = new PageHandler();
    }

    public void takeAGuess(String guess) {
        if (inputCheck(guess)){
            inputErr = inputCheck(guess);
        }else {
            inputErr = inputCheck(guess);
            currentGuess = Integer.parseInt(guess);
            guessGameModel.clientGuess(currentGuess);
            currentState = currentGameState();
        }
    }
    public void newGame() {
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
                return pageHandler.errMsg();
        return pageHandler.updateResult(currentGameState());
    }
}
