public class PageHandler extends ExceptionHandler {
    /**
     * This method informs the client about wrong type input
     */
    public String errMsg() {
        return "<p>Wrong type input</p>";
    }

    /**
     * This method returns html code to display results
     *
     * @param result           [High, Success, Low]
     * @param numberOfAttempts number of attemts
     */

    public String handleTry(String result, int numberOfAttempts) {
        return "<p>" + result + "</p>"
                    + "<p>Number of Attempts: " + numberOfAttempts + "</p>";
    }

    public String handleSuccess(String result, int numberOfAttempts) {
        return "<p>" + result + "</p>"
                + "<p>It took you " + numberOfAttempts + " guesses to guess the number.</p>";
    }
}