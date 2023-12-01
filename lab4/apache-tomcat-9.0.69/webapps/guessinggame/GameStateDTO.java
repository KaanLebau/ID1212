/**
 * This class is used to send data between different java classes
 * @author Kaan Özsan
 * @author Dan Ljungström
 */
public class GameStateDTO {
    int numberOfAttempt;
    int currentGuess;
    boolean gameIsOn;
    String result;
    private GuessGameModel guessGameModel;

    public GameStateDTO(){
        this.result = guessGameModel.getResult();
    }
    public GameStateDTO(GuessGameModel guessGameModel , int currentGuess){
        this.numberOfAttempt = guessGameModel.getAttempt();
        this.currentGuess = currentGuess;
        this.gameIsOn = guessGameModel.isGameIsOn();
        this.result = guessGameModel.getResult();

    }

    public GameStateDTO theState(){
        return this;
    }


    public String toString(){
        String state= "";
        state += "Current game state " + gameIsOn +"\n";
        state += "Current guess is: " + currentGuess +"\n";
        state += "Current attempt is: " + numberOfAttempt +"\n";
        if(gameIsOn){
            state += "Game is not ended yet.";
        }else {
            state += "Game is ended.";
        }

        return state;
    }


}
