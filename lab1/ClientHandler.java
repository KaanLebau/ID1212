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
    public  ClientHandler(Socket client) {
        try{
            this.client = client;
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),true);
            exceptionhandler = new ExceptionHandler();
        }catch (IOException e){

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
        for (ClientHandler client : clients){
            try{
                if(client.getClientSocket().getPort() != getClientSocket().getPort()){
                    client.out.println(msg);
                }

            }catch (Exception e){
                exceptionhandler.clientOutHandler(e.toString());
            }finally {
                closeConnection(client.getClientSocket(), in, out);
            }
        }
    }

    private void closeConnection(Socket client, BufferedReader in, PrintWriter out) {

        try {
            closeReader(in);
            closeWriter(out);
            closeSocket(client);
            removeClient();
        } catch (IOException e) {
            exceptionhandler.clientClosingException(e.toString());
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
