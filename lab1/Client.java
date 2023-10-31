import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ExceptionHandler exceptionHandler;
    private String serverAddress;

    public Client(Socket socket){
        exceptionHandler = new ExceptionHandler();
        try{
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
        }catch (Exception e){
            exceptionHandler.clientCreationException(e.toString());

        }

    }





}
