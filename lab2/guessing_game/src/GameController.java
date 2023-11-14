public class GameController {

    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private int numberofgames;
    private int sumOfAttemts;
    private double successRatio;
    private int currentGuess;

    public GameController(GuessGameModel gameModel){
        guessGameModel = gameModel;
        pageHandler = new PageHandler();
    }
    public void takeAGuess(String guess){
        System.out.println(guess);
        if (inputCheck(guess))
            pageHandler.errMsg();
        currentGuess = Integer.parseInt(guess);
        validate(guessGameModel.clientGuess(currentGuess));
    }
    //TODO
    private boolean inputCheck(String input){
        return false;
    }

    public void validate(String result){
        pageHandler.handlePageUpdate(result, guessGameModel.getAttempt());
        if(!guessGameModel.isGameIsOn())
            calculateScore();
    }

    public GameStateDTO currentGameState(){
        return new GameStateDTO(guessGameModel.getAttempt(), currentGuess, guessGameModel.isGameIsOn());
    }
    private void calculateScore(){
        sumOfAttemts += guessGameModel.getAttempt();
        numberofgames++;
        successRatio = sumOfAttemts / numberofgames;
    }



}
