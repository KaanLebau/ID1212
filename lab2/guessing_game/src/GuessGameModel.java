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

    public void clientGuess(int guess){
        if(!gameIsOn)
            newGame();
        attempt++;
        if(theNumber == guess){
            correctResponse(guess);
        }else{
            wrongResponse(guess);
        }
    }

    public void newGame(){
        int lastGameNumber = this.theNumber;
        attempt = 0;
        while(true){
            theNumber = (int) ((Math.random() * (100 - 0)) + 0);
            if (theNumber != lastGameNumber)
                break;
        }
    }

    private void wrongResponse(int guess) {
        if(guess < theNumber){
            System.out.println(guess + " is to low");
        }else {
            System.out.println(guess + "is to high");
        }
    }

    private int correctResponse(int guess) {
        System.out.println("this is the correct one");
        gameIsOn = false;
        return attempt;
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
