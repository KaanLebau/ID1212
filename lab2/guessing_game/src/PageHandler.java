public class PageHandler extends ExceptionHandler {
    /**
     * This method informs the client about wrong type input
     */
    public String errMsg() {
        return "<p>Incorrect input. You have to enter a number between 0 and 100.</p>";
    }

    /**
     * This method returns html code to display results
     *
     * @param GameStateDTO gameStateDTO
     */
    public String updateResult(GameStateDTO gameStateDTO) {
        if(gameStateDTO.gameIsOn)
            return "<p>" + gameStateDTO.result + "</p>"
                    + "<p>Number of Attempts: " + gameStateDTO.currentGuess + "</p>";

        return "<p>" + gameStateDTO.result + "</p>"
                + "<p>It took you " + gameStateDTO.currentGuess + " guesses to guess the number.</p>";
    }
}