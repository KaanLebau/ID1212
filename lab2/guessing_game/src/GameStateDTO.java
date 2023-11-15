public class GameStateDTO {
    int numberOfAttempt;
    int currentGuess;
    boolean gameIsOn;

    public GameStateDTO(int numberOfAttempt, int currentGuess, boolean gameIsOn){
        this.numberOfAttempt = numberOfAttempt;
        this.currentGuess = currentGuess;
        this.gameIsOn = gameIsOn;
    }

    public GameStateDTO theState(){
        return this;
    }

    public String toString(){
        String state= "";
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
