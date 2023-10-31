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
     *TODO
     * @param errMsg
     */
    public void clientSocketHandler(String errMsg){
       new ClientSocketException(errMsg);
    }

    /**
     *
     * @param errMsg
     */
    public void clientClosingException(String errMsg){
        new ClientClosingException(errMsg);
    }

    /**
     *
     */
    private class ClientCreationException {
        public ClientCreationException(String errMsg) {
        }
    }

    /**
     *TODO
     */
    private class UserInputException extends IOException{
        public UserInputException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during .....");//TODO fill ....
        }
    }

    /**
     *TODO
     */
    private class UserOutputException extends IOException{
        public UserOutputException(String errMsg) {
            super(errMsg);
            System.out.println("Some thing went wrong during .....");//TODO fill ....
        }
    }

    /**
     *TODO
     */
    private class ServerInputException extends IOException{
        public ServerInputException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during .....");//TODO fill ....
        }
    }

    /**
     *TODO
     */
    private class ServerOutputException extends IOException{
        public ServerOutputException(String errMsg) {
            super(errMsg);
            System.out.println("Some thing went wrong during .....");//TODO fill ....
        }
    }

    /**
     * TODO
     */
    private class ServerSocketException extends IOException{
        public ServerSocketException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during .....");//TODO fill ....
        }
    }

    /**
     * TODO
     */
    private class ClientSocketException extends IOException{
        public ClientSocketException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during .....");//TODO fill ....
        }

    }
    /**
     * TODO
     *
     */
    private class ClientClosingException extends IOException{
        public ClientClosingException(String errMsg){
            super(errMsg);
            System.out.println("Some thing went wrong during .....");//TODO fill ....
        }
    }

}
