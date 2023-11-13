public class GameController {

    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private ConnectionHandler connectionHandler;
    private CookieHandler cookieHandler;
    private GameStateDTO curentState;
    private int numberofgames;
    private int sumOfAttemts;
    private double successRatio;
    private int currentGuess;
    public GameController(ConnectionHandler connectionHandler, CookieHandler cookieHandler){
        guessGameModel = new GuessGameModel();
        pageHandler = new PageHandler();
        this.connectionHandler = connectionHandler;
        this.cookieHandler = cookieHandler;

    }
    public void takeAGuess(String guess){
        if (inputCheck(guess))
            pageHandler.errMsg();
        currentGuess = Integer.parseInt(guess);
        System.out.println("guess from Controller"+ currentGuess);
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
            cookieHandler.update_cookie(this.numberofgames, this.sumOfAttemts, this.successRatio);
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
