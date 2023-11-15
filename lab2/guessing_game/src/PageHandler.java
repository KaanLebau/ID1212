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
    public String updateResult(GameStateDTO gameStateDTO, GameHistoryDTO [] gameHistoryDTO) {
        if(gameStateDTO.gameIsOn)
            return "<div className=\"result\"><p>" + gameStateDTO.result + "</p>"
                    + "<p>Number of Attempts: " + gameStateDTO.currentGuess + "</p></div>"
                    + updateTable(gameHistoryDTO);

        return "<div className=\"result\"><p>" + gameStateDTO.result + "</p>"
                + "<p>It took you " + gameStateDTO.currentGuess + " guesses to guess the number.</p></div>"
                + updateTable(gameHistoryDTO);
    }

    public String updateTable(GameHistoryDTO [] allUserHistory){
        String table = "<div className=\"table\">"
            + "<table class=\"tg\">"
            + "<thead>"
            + "<tr>"
                + "<th>Data</th>"
                + "<th>User</th>"
                + "<th>All</th>"
              + "</tr>"
            + "<thead>"
            + "<tbody>"
              + " <tr>"
                + "<td>Total Attempts</td>"
                + "<td>" + allUserHistory[1].getSumOfAttempts() +"</td>"
                + "<td>" + allUserHistory[0].getSumOfAttempts() +"</td>"
              + " </tr>"
              + " <tr>"
                + "<td>Total Games</td>"
                + "<td>" + allUserHistory[1].getNumberOfGames() +"</td>"
                + "<td>" + allUserHistory[0].getNumberOfGames() +"</td>"
              + " </tr>"
              + " <tr>"
                + "<td>Average Guesses</td>"
                + "<td>" + allUserHistory[1].getAverageGuesses() +"</td>"
                + "<td>" + allUserHistory[0].getAverageGuesses() +"</td>"
              + "</tr>"
            + "</tbody>"
            + "</table>"
            + "</div>";

        return table;
    }
}