import java.io.IOException;

public class ExceptionHandler {

    public ExceptionHandler(){
    }

    /**
     *
     * @param errMsg
     */
    public void clientCreationException(String errMsg){
       new ClientCreationException(errMsg);
    }

    /**
     * TODO
     * @param errMsg
     */
    public void clientOutHandler(String errMsg){
        new UserOutputException(errMsg);
    }

    /**
     *TODO
     * @param errMsg
     */
    public void clientInHandler(String errMsg){
       new UserInputException(errMsg);
    }

    /**
     *TODO
     * @param errMsg
     */
    public void serverInHandler(String  errMsg){
        new ServerInputException(errMsg);
    }

    /**
     *TODO
     * @param errMsg
     */
    public void serverOutHandler(String errMsg){
        new ServerOutputException(errMsg);
    }

    /**
     *TODO
     * @param errMsg
     */
    public void serverSocketHandler(String errMsg){
        new ServerSocketException(errMsg);
    }

        /**
     *
     * @param errMsg
     */
    public void serverCreationException(String errMsg){
        new ServerCreationException(errMsg);
    }

        /**
     *
     * @param errMsg
     */
    public void serverInitException(String errMsg){
        new ServerInitException(errMsg);
    }

    /**
     *
     * @param errMsg
     */
    public void clientSocketHandler(String errMsg){
       new ClientSocketException(errMsg);
    }

        /**
     *
     * @param errMsg
     */
    public void clientSendHandler(String errMsg){
        new ClientSendException(errMsg);
    }

    /**
     *
     * @param errMsg
     */
    public void clientClosingHandler(String errMsg){
        new ClientClosingException(errMsg);
    }

    /**
     *
     * @param errMsg
     */
    public void chCreationHandler(String errMsg){
        new ChCreationException(errMsg);
    }

    /**
     *
     */
    private class ClientCreationException {
        public ClientCreationException(String errMsg) {
        }
    }

    private class UserInputException extends IOException{
        public UserInputException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during user input");
        }
    }

    private class UserOutputException extends IOException{
        public UserOutputException(String errMsg) {
            super(errMsg);
            System.out.println("Some thing went wrong during user output");
        }
    }

    private class ServerInputException extends IOException{
        public ServerInputException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during server input");
        }
    }

    private class ServerOutputException extends IOException{
        public ServerOutputException(String errMsg) {
            super(errMsg);
            System.out.println("Some thing went wrong during server output");
        }
    }

    private class ServerSocketException extends IOException{
        public ServerSocketException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong with the server socket");
        }
    }

    private class ClientSocketException extends IOException{
        public ClientSocketException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong with the cient socket");
        }

    }

    private class ClientSendException extends IOException{
        public ClientSendException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during client sending");
        }
    }

    private class ClientClosingException extends IOException{
        public ClientClosingException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during client closing");
        }
    }

    private class ServerCreationException extends IOException{
        public ServerCreationException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during the server creation");
        }
    }

    private class ServerInitException extends IOException{
        public ServerInitException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during server initialization");
        }
    }

    private class ChCreationException extends IOException{
        public ChCreationException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during the client handler creation");
        }
    }

}
