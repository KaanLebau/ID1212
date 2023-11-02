import java.io.*;
import java.net.Socket;
import java.util.*;
/**
 *
 *
 *  @author Kaan Özsan
 *  @author Dan Ljungström
 */
public class Client extends ExceptionHandler{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ExceptionHandler exceptionHandler;
    private String username;
    private boolean done = false;
    private  String serverAddress = "localhost";

    private Scanner scanner;
    private final int PORT = 8088;

    public Client(String username, String serverAddress){
        exceptionHandler = new ExceptionHandler();
        this.username = username;
        if(serverAddress != "")
            this.serverAddress = serverAddress;
        try{
            this.socket = new Socket(this.serverAddress, PORT);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(),true);


        }catch (Exception e){
            creationHandler(e,"Client");
            closeConnection(socket, in, out);
        }
    }

    public void sendMessage() {
        try{
            //Connect to the chat and enter username
            out.println(username);

            scanner = new Scanner(System.in);

            while(!done && !socket.isClosed()) {
                String message = scanner.nextLine();
                out.println(this.username + ": " + message);

                if(message.startsWith("/quit")){
                    done = true;
                    break;
                }
            }
            System.out.println("Disconnected.");
        } catch (Exception e) {
            outHandler(e,"Client");
            closeConnection(socket, in, out);
        }
    }

    public void getMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String incommingMsg;

                while(!done && !socket.isClosed()){
                    try{
                        incommingMsg = in.readLine();
                        System.out.println(incommingMsg);
                    } catch (IOException e){
                        inHandler(e, "Client");
                        closeConnection(socket, in, out);
                    }
                }
            }
        }).start();
    }
    private void closeConnection(Socket client, BufferedReader in, PrintWriter out) {
        try {
            closeReader(in);
            closeWriter(out);
            closeSocket(client);
        } catch (IOException e) {
            closingHandler(e,"Client");
        }

    }

    private void closeSocket(Socket client) throws IOException {
        if(client != null){
            client.close();
        }

    }
    private void closeReader(BufferedReader in) throws IOException {
        if(in != null){
            in.close();
        }
    }
    private void closeWriter(PrintWriter out){
        if(out != null){
            out.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.nextLine();

        System.out.println("Enter Chat server address: ");
        String serverAddress = sc.nextLine();
        

        Client client = new Client(username, serverAddress);
        client.getMessage();
        client.sendMessage();
    }
}
