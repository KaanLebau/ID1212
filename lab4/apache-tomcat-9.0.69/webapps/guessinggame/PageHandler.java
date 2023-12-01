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
    public String updateResult(GameStateDTO gameStateDTO, GameHistoryDTO [] gameHistoryDTO) {
        if(gameStateDTO.gameIsOn)
            return "<div className=\"result\"><p>" + gameStateDTO.result + "</p>"
                    + "<p>Number of Attempts: " + gameStateDTO.currentGuess + "</p></div>"
                    + updateTable(gameHistoryDTO);

        return "<div className=\"result\"><p>" + gameStateDTO.result + "</p>"
                + "<p>It took you " + gameStateDTO.currentGuess + " guesses to guess the number.</p></div>"
                + updateTable(gameHistoryDTO);
    }

    /**
     * This method returns called by <code>GameController</code> to update table
     * @param allUserHistory
     * @return table body with data from <code> GameController <\code>
     */
    public String updateTable(GameHistoryDTO [] allUserHistory){
        String table = "<style type=\"text/css\">"
                +".tg  {border-collapse:collapse;border-spacing:0; margin:5px 0px 0px 50px;}"
                +".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;"
                +"  overflow:hidden;padding:10px 5px;word-break:normal;}"
                +".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;"
                +"  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}"
                +".tg .tg-baqh{text-align:center;vertical-align:top}"
                +".tg .tg-0lax{text-align:left;vertical-align:top}"
                +"</style>"
                +"<table class=\"tg\" style=\"undefined;table-layout: fixed; width: 291px\">"
                +"<colgroup>"
                +"<col style=\"width: 149px\">"
                +"<col style=\"width: 71px\">"
                +"<col style=\"width: 71px\">"
                +"</colgroup>"
                +"<thead>"
                +"  <tr>"
                +"    <th class=\"tg-baqh\"></th>"
                +"    <th class=\"tg-baqh\">user </th>"
                +"    <th class=\"tg-baqh\">ALL</th>"
                +"  </tr>"
                +"</thead>"
                +"<tbody>"
                +"  <tr>"
                +"    <td class=\"tg-0lax\">Total Attempts </td>"
                +"    <td class=\"tg-baqh\">"+ allUserHistory[1].getSumOfAttempts() +"</td>"
                +"    <td class=\"tg-baqh\">"+ allUserHistory[0].getSumOfAttempts() +"</td>"
                +"  </tr>"
                +"  <tr>"
                +"    <td class=\"tg-0lax\">Total Games</td>"
                +"    <td class=\"tg-baqh\">"+ allUserHistory[1].getNumberOfGames() +"</td>"
                +"    <td class=\"tg-baqh\">"+ allUserHistory[0].getNumberOfGames() +"</td>"
                +"  </tr>"
                +"  <tr>"
                +"    <td class=\"tg-0lax\">Average Guesses</td>"
                +"    <td class=\"tg-baqh\">"+allUserHistory[1].getAverageGuesses()+"</td>"
                +"    <td class=\"tg-baqh\">"+ allUserHistory[0].getAverageGuesses() +"</td>"
                +"  </tr>"
                +"</tbody>"
                +"</table>";

        return table;
    }
}