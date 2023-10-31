import java.awt.print.PrinterGraphics;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer{
    private final int PORT = 8088;
    private ServerSocket serverSocket;
    private ClientHandler client;
    private ExceptionHandler exceptionHandler;
    
    public ChatServer(){
        try {
            this.serverSocket = new ServerSocket(PORT);
            this.exceptionHandler = new ExceptionHandler();

        }catch(IOException e){
            exceptionHandler.serverCreationException(e.toString());
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
            exceptionHandler.serverInitException(e.toString());
        }
}



    public static void main(String[] args) throws IOException {
        ChatServer cs = new ChatServer();

        System.out.println("Server is running");
    }
}

