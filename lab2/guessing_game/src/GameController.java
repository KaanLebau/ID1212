public class GameController {

    private boolean inputErr;
    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private GameStateDTO currentState;
    private int currentGuess;
    private int numberOfGames = 1;
    private int sumOfAttempts;
    private double averageGuesses;

    public GameController() {
        guessGameModel = new GuessGameModel();
        pageHandler = new PageHandler();
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
        System.out.println(currentGuess);
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
        return pageHandler.updateResult(currentGameState());
    }
    public void getAllUserHistory(GameHistoryDTO allUserHistory){
        GameHistoryDTO[] historyList = new GameHistoryDTO[2];
        historyList[0] = allUserHistory;
        historyList[1] = new GameHistoryDTO(numberOfGames, sumOfAttempts, averageGuesses);
         pageHandler.updateTable(historyList);
    }


}
