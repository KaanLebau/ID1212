public class GameController {

    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private int numberofgames;
    private int sumOfAttemts;
    private double successRatio; //TODO
    private int currentGuess;

    public GameController() {
        guessGameModel = new GuessGameModel();
        pageHandler = new PageHandler();
    }

    public String takeAGuess(String guess) {
        if (inputCheck(guess))
            pageHandler.errMsg();
        currentGuess = Integer.parseInt(guess);
        return validate(guessGameModel.clientGuess(currentGuess));
    }

    public void newGame() {
        guessGameModel.newGame();
    }

    // TODO
    private boolean inputCheck(String input) {
        return false;
    }

    public String validate(String result) {
        if (!guessGameModel.isGameIsOn()){
            calculateScore();
            return pageHandler.handleSuccess(result, guessGameModel.getAttempt());
        }
        else
            return pageHandler.handleTry(result, guessGameModel.getAttempt());
    }

    public GameStateDTO currentGameState() {
        return new GameStateDTO(guessGameModel.getAttempt(), currentGuess, guessGameModel.isGameIsOn());
    }

    //TODO
    private void calculateScore() {
        sumOfAttemts += guessGameModel.getAttempt();
        numberofgames++;
        successRatio = sumOfAttemts / numberofgames;
    }
}
