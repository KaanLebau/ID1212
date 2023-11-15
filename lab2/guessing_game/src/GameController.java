public class GameController {

    private boolean inputErr;
    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private GameStateDTO currentState;
    private GameHistoryDTO [] allUserHistory;
    private int currentGuess;
    private int numberOfGames = 1;
    private int sumOfAttempts;
    private double averageGuesses;

    public GameController() {
        guessGameModel = new GuessGameModel();
        pageHandler = new PageHandler();
        allUserHistory = new GameHistoryDTO[2];
    }

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
                return pageHandler.errMsg();
        return pageHandler.updateResult(currentGameState(), allUserHistory);
    }
    public void getAllUserHistory(){
        pageHandler.updateTable(allUserHistory);
    }

    public GameHistoryDTO getHistory(){
        allUserHistory[1] = new GameHistoryDTO(numberOfGames, sumOfAttempts, averageGuesses);
        return allUserHistory[1];
    }

    public void updateServerHistory(GameHistoryDTO gameHistoryDTO){
        allUserHistory[0] = gameHistoryDTO;
    }
}
