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
    public String clientGuess(int guess){
        if(!gameIsOn)
            newGame();
        attempt++;
        if(theNumber == guess){
            return correctResponse(guess);
        }else{
            return wrongResponse(guess);
        }
    }
    public int getAttempt(){ return attempt;}

    public boolean isGameIsOn() {return gameIsOn;}

    public void newGame(){
        int lastGameNumber = this.theNumber;
        attempt = 0;
        while(true){
            theNumber = (int) ((Math.random() * (100 - 0)) + 0);
            if (theNumber != lastGameNumber)
                break;
        }
    }

    private String wrongResponse(int guess) {
        if(guess < theNumber){
            return "Low";
        }else {
            return"High";
        }
    }

    private String correctResponse(int guess) {
        System.out.println("this is the correct one");
        gameIsOn = false;
        return "Success";
    }


    public static void main(String[] args) {
        GuessGameModel ggm = new GuessGameModel();
        System.out.println(ggm.theNumber);
        ggm.newGame();
        System.out.println(ggm.theNumber);
        ggm.newGame();
        System.out.println(ggm.theNumber);
    }

}
