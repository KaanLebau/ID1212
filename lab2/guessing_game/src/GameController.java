public class GameController {

    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private ConnectionHandler connectionHandler;
    private CookieHandler cookieHandler;
    private int numberofgames;
    private int sumOfAttemts;
    private double successRatio;
    public GameController(ConnectionHandler connectionHandler, CookieHandler cookieHandler){
        guessGameModel = new GuessGameModel();
        pageHandler = new PageHandler();
        this.connectionHandler = connectionHandler;
        this.cookieHandler = cookieHandler;

    }
    public void takeAGuess(String guess){
        if (inputCheck(guess))
            pageHandler.errMsg();
        System.out.println("guess from Controller"+ Integer.parseInt(guess));
        validate(guessGameModel.clientGuess(Integer.parseInt(guess)));
    }
    //TODO
    private boolean inputCheck(String input){
        return false;
    }

    public void validate(String result){
        pageHandler.handlePageUpdate(result, guessGameModel.getAttempt());
        connectionHandler.requestPageRerender();
        if(!guessGameModel.isGameIsOn())
            calculateScore();
            cookieHandler.update_cookie(this.numberofgames, this.sumOfAttemts, this.successRatio);
    }
    private void calculateScore(){
        sumOfAttemts += guessGameModel.getAttempt();
        numberofgames++;
        successRatio = sumOfAttemts / numberofgames;
    }

}
