package model;

/**
 *
 * This model handles guessing game logic.
 *<ul>
 *
 * <li>Creates an instance of game<\li>
 * <li>Handles the guess if it is correct or wrong<\li>
 * <li>Counts number of attempts<\li>
 * <li>Keeps game status whether the game is active or not<\li>
 * </ul>
 * @author Kaan Özsan
 * @author Dan Ljungström
 */
public class GuessGameModel {
    private int theNumber;

    private int attempt;
    private boolean gameIsOn;
    private String result;

    public GuessGameModel(){
        this.theNumber = (int) ((Math.random() * (100 - 0)) + 0);
        this.gameIsOn = true;
        attempt = 0;
    }

    /**
     * This method receives an integer from Controller
     * returns following results
     * <ul>
     *     <li>High</li>
     *     <li>Success</li>
     *     <li>Low</li>
     * </ul>
     *
     * @param guess clients guess
     */
    public void clientGuess(int guess){
        if(!gameIsOn)
            newGame();
        attempt++;
        response(guess);
    }
    public int getAttempt(){
        return attempt;
    }

    public boolean isGameIsOn() {return gameIsOn;}
    public String getResult(){return result;}

    /**
     * Creates new game
     */
    public void newGame(){
        int lastGameNumber = this.theNumber;
        gameIsOn = true;
        attempt = 0;
        while(true){
            theNumber = (int) ((Math.random() * (100 - 0)) + 0);
            if (theNumber != lastGameNumber)    
                break;
        }
    }

    public int getTheNumber(){
        return theNumber;
    }

    private void response(int currentGuess){
        if(currentGuess == theNumber){
            gameIsOn = false;
            this.result = "Success! " + currentGuess + " was the number!";
        } else if (currentGuess < theNumber) {
            this.result = currentGuess + " is lower than the number.";
        }else {
            this.result = currentGuess + " is higher than the number.";
        }

    }

    public String toString(){
        String model = "*********************\n";
        model+= "Secret num: "+theNumber+ "\n";

        model+= "Num of attempt:" + attempt+ "\n";
        model+= "game state: " + gameIsOn+ "\n";
        model+= "game result : "+ result+ "\n";
        model += "*********************\n";
        return model;
    }

}