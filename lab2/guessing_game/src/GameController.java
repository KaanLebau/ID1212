public class GameController {

    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private int numberofgames;
    private int sumOfAttemts;
    private double successRatio;
    private int currentGuess;

    public GameController(GuessGameModel gameModel) {
        guessGameModel = gameModel;
        pageHandler = new PageHandler();
    }

    public String takeAGuess(String guess) {
        if (inputCheck(guess))
            pageHandler.errMsg();
        currentGuess = Integer.parseInt(guess);
        return validate(guessGameModel.clientGuess(currentGuess));
    }

    // TODO
    private boolean inputCheck(String input) {
        return false;
    }

    public String validate(String result) {
        if (!guessGameModel.isGameIsOn()){
            calculateScore();
            return pageHandler.handlePageUpdate(result, guessGameModel.getAttempt());
        }
        else
            return pageHandler.handlePageUpdate(result, guessGameModel.getAttempt());
    }

    public GameStateDTO currentGameState() {
        return new GameStateDTO(guessGameModel.getAttempt(), currentGuess, guessGameModel.isGameIsOn());
    }

    private void calculateScore() {
        sumOfAttemts += guessGameModel.getAttempt();
        numberofgames++;
        successRatio = sumOfAttemts / numberofgames;
    }

}
