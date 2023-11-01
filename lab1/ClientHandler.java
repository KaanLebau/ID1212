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

            System.out.println("New user: " + this.username + ". Number of users: " + clients.size());
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
                closeConnection(client, in, out);
                break;
            }
        }
    }

    public void broadcast(String msg){
        msg = messageCheck(msg);
        for (ClientHandler client : clients){
            try{
                if(client.username != this.username){
                    System.out.println("Broadcasting: " + msg );
                    client.out.println(msg);
                }

            }catch (Exception e){
                exceptionhandler.clientOutHandler(e.toString());
                closeConnection(client.client, client.in, client.out);
            }
        }
    }
    public String messageCheck(String msg){
        String info = msg;
        if(msg.startsWith(this.username + ": /quit")){
            info = (this.username + " disconnected.");
            closeConnection(this.client, this.in, this.out);
            return info;
        }else {
            return info;
        }

    }
    private void closeConnection(Socket client, BufferedReader in, PrintWriter out) {
        try {
            for(ClientHandler arrclient : clients){
            System.out.println(arrclient.username);
            }
            System.out.println("Socket is connected: " + client.isConnected());
            closeReader(in);
            closeWriter(out);
            closeSocket(client);
            removeClient();
            System.out.println("Socket is connected: " + client.isConnected());
        } catch (IOException e) {
            System.out.println("shit");
            exceptionhandler.clientClosingHandler(e.toString());
        }

    }

    private void closeSocket(Socket client) throws IOException {
        if(client != null){
            System.out.println("Closing socket: " + client);
            client.close();
        }

    }
    private void closeReader(BufferedReader in) throws IOException {
        if(in != null){
            System.out.println("Closing reader: " + in);
            in.close();
        }
    }
    private void closeWriter(PrintWriter out){
        if(out != null){
            System.out.println("Closing writer: " + out);
            out.close();
        }
    }

    private void removeClient(){
        System.out.println("Connected clients: " + clients.size());
        System.out.println("Removing client: " + this.username);
        clients.remove(this);
        System.out.println("Connected clients: " + clients.size());
    }

}
