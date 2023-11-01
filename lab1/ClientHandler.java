import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clients = new ArrayList<>();
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ChatServer server;
    private ExceptionHandler exceptionhandler;
    private String message;
    private String username;

    public  ClientHandler(Socket client) {
        try{
            this.client = client;
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(client.getOutputStream(),true);
            this.exceptionhandler = new ExceptionHandler();
            this.username = in.readLine();
            clients.add(this);

            System.out.println("new user > " + this.username);
            broadcast(this.username + " has joined the chat.");
            
        }catch (IOException e){
            exceptionhandler.chCreationHandler(e.toString());
        }
    }

    public Socket getClientSocket(){
        return client;
    }

    @Override
    public void run() {
        String message;

        while(client.isConnected()){
            try {
                message = in.readLine();
                broadcast(message);
            } catch (IOException e) {
                exceptionhandler.clientInHandler(e.toString());
                break;
            }
        }
    }

    public void broadcast(String msg){
        msg = messageCheck(msg);
        for (ClientHandler client : clients){
            try{
                if(client.username != this.username){
                    System.out.println("Broadcasting: " + this.username + " = " + msg );
                    client.out.println(this.username + ": " + msg);
                }

            }catch (Exception e){
                exceptionhandler.clientOutHandler(e.toString());
                closeConnection(client.client, client.in, client.out);
            }
        }
    }
    public String messageCheck(String msg){
        String info = msg;
        if(msg.startsWith("/quit")){
            info = "You are disconnecting from server";
            closeConnection(this.client, this.in, this.out);
            return info;
        }else {
            return info;
        }

    }
    private void closeConnection(Socket client, BufferedReader in, PrintWriter out) {
        try {
            closeReader(in);
            closeWriter(out);
            closeSocket(client);
            removeClient();
        } catch (IOException e) {
            System.out.println("shit");
            exceptionhandler.clientClosingHandler(e.toString());
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

    private void removeClient(){
        clients.remove(this.client);
    }

}
