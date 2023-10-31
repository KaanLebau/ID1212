import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ExceptionHandler exceptionHandler;
    private String username;
    private static final String serverAddress = "localhost";
    private static final int PORT = 8088;

    public Client(Socket socket, String username){
        exceptionHandler = new ExceptionHandler();
        try{
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(),true);
            this.username = username;

        }catch (Exception e){
            exceptionHandler.clientCreationException(e.toString());
        }
    }

    public void sendMessage() {
        try{
            //Connect to the chat and enter username
            out.write(username);

            Scanner sc = new Scanner(System.in);

            while(socket.isConnected()) {
                String message = sc.nextLine();
                out.write(username + ": " + message);
            }
        } catch (Exception e) {
            exceptionHandler.clientSendHandler(e.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        
        Socket socket = new Socket(serverAddress, PORT);
        Client client = new Client(socket, username);
    }
}
