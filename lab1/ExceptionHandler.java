import java.io.IOException;

public class ExceptionHandler {

    /**
     * TODO
     * @param errMsg message contains which class throws the exception
     */
    public void outHandler(Exception e, String errMsg){
        new OutputException(e, errMsg);
    }

    /**
     * TODO
     * @param errMsg message contains which class throws the exception
     */
    public void inHandler(Exception e, String errMsg){
       new InputException(e, errMsg);
    }

    /**
     * <p>
     * Handling exceptions can occur while initializing server and any exception during new client trying to connect to server.
     * </p>
     * @param errMsg message contains which class throws the exception
     */
    public void initializeHandler(Exception e, String errMsg){
        new InitializeException(e, errMsg);
    }

    /**
     *
     * <p>
     * Handles exceptions while closing connection the following classes <i><b>ClientHandler, Client</b></i>.
     * </p>
     * @param errMsg message contains which class throws the exception
     */
    public void closingHandler(Exception e, String errMsg){
        new ClosingException(e, errMsg);
    }

    /**
     * <p>
     * Handles exceptions while creating the following classes <i><b>Chatserver, ClientHandler, Client</b></i>.
     * </p>
     * @param errMsg message contains which class throws the exception
     */
    public void creationHandler(Exception e, String errMsg){
        new CreationException(e, errMsg);
    }
    private class CreationException extends IOException{
        public CreationException(Exception e, String errMsg) {
            super(e);
            System.out.println("Some thing went wrong during creation of "+errMsg);
        }
    }
    private class InputException extends IOException{
        public InputException(Exception e, String errMsg){
            super(e);
            System.out.println("Some thing went wrong during "+errMsg+" input");
        }
    }
    private class OutputException extends IOException{
        public OutputException(Exception e, String errMsg) {
            super(e);
            System.out.println("Some thing went wrong during "+ errMsg +" output");
        }
    }
    private class InitializeException extends IOException{
        public InitializeException(Exception e, String errMsg){
            super(e);
            System.out.println("Some thing went wrong with the "+errMsg+" during ");
        }
    }
    private class ClosingException extends IOException{
        public ClosingException(Exception e, String errMsg){
            super(e);
            System.out.println("Some thing went wrong during "+errMsg+" closing");
        }
    }



}
