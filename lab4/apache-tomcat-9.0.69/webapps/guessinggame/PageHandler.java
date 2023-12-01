/**
 * This class updates the <code>index.html</code> file with new data from the <code>GameController</code>
 *  @author Kaan Özsan
 *  @author Dan Ljungström
 */
public class PageHandler extends ExceptionHandler {
    /**
     * This method informs the client about wrong type input
     */
    public String errMsg() {
        return "<p>Incorrect input. You have to enter a number between 0 and 100.</p>";
    }

    /**
     * This method returns html code to display results in <code>index.html</code>
     *
     * @param gameStateDTO
     */
    public String updateResult(GameStateDTO gameStateDTO) {
        if(gameStateDTO.gameIsOn)
            return "<div className=\"result\"><p>" + gameStateDTO.result + "</p>"
                    + "<p>Number of Attempts: " + gameStateDTO.currentGuess + "</p></div>";

        return "<div className=\"result\"><p>" + gameStateDTO.result + "</p>"
                + "<p>It took you " + gameStateDTO.currentGuess + " guesses to guess the number.</p></div>";
    }
}