public class GameController {

    private boolean inputErr;
    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private int numberofgames;
    private int sumOfAttemts;
    private double successRatio; //TODO
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
        System.out.println(currentGuess);
    }
    public void newGame() {
        guessGameModel.newGame();
    }

    private boolean inputCheck(String input) {
        try{
            if(Integer.parseInt(input) > 100 && Integer.parseInt(input) <= 0)
                return true;

            return false;
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

    //TODO
    private void calculateScore() {
        sumOfAttemts += guessGameModel.getAttempt();
        numberofgames++;
        successRatio = sumOfAttemts / numberofgames;
    }
}
