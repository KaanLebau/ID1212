public class GameController {

    private GuessGameModel guessGameModel;
    private PageHandler pageHandler;
    private ConnectionHandler connectionHandler;
    public GameController(ConnectionHandler connectionHandler){
        guessGameModel = new GuessGameModel();
        pageHandler = new PageHandler();
        this.connectionHandler = connectionHandler;

    }
    public void takeAGuess(String guess){

        System.out.println("guess from Controller"+ Integer.parseInt(guess));
        validate(guessGameModel.clientGuess(Integer.parseInt(guess)));
    }

    public void validate(String result){
        pageHandler.handlePageUpdate(result, guessGameModel.getAttempt());
        connectionHandler.requestPageRerender();
    }

}
