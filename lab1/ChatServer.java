import java.awt.print.PrinterGraphics;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 *
 *  @author Kaan Özsan
 *  @author Dan Ljungström
 */
public class ChatServer extends ExceptionHandler{
    private final int PORT = 8088;
    private ServerSocket serverSocket;
    private ClientHandler client;

    
    public ChatServer(){
        try {
            this.serverSocket = new ServerSocket(PORT);
        }catch(IOException e){
            creationHandler(e, "Server");
        }
    }
public void initialize(){
        try {
            while(!serverSocket.isClosed()){
                client = new ClientHandler(serverSocket.accept());
                Thread ch = new Thread(client);
                ch.start();
            }
        }catch (Exception e){
            initializeHandler(e, "Server");
        }
}

    public static void main(String[] args) throws IOException {
        ChatServer cs = new ChatServer();

        System.out.println("Server is running ");
        cs.initialize();
    }
}

