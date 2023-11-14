public class PageHandler extends ExceptionHandler{
    /**
     * This method informs the client about wrong type input
     */
    public void errMsg(){}

    /**
     * This method writes over the index.html file with current game status
     *
     * @param result [High, Success, Low]
     * @param numberOfAttempts number of attemts
     */

        public String handlePageUpdate(String result, int numberOfAttempts) {
            String additionalData ="";
            if(!result.contains("Success")){
                additionalData = "<p>" + result + "</p>"
                        + "<p>Number of Attempts: " + numberOfAttempts + "</p>";
            }else{
                additionalData = "<p>" + result + "</p>"
                        + "<p>It took you " + numberOfAttempts + " guesses to guess the number.</p>";
            }
            return additionalData;
    }
}